setInterval(updateDate, 1000);
function updateDate(){
    const options = {year:`numeric`, month:`2-digit`, day:`2-digit`, hour:`2-digit`, minute:`2-digit`, second:`2-digit`};
    let dateText = new Date().toLocaleDateString("ro-ro",options);
    let oldDate = document.getElementsByName(`date`)[0];
    let newDate = document.createElement(`div`);
    newDate.setAttribute(`class`,`Sale-text`);
    newDate.setAttribute(`name`,`date`);

    newDate.textContent = dateText;
    oldDate.parentNode.replaceChild(newDate,oldDate);
}

function ChangeItem(index,action)
{
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/demo-1.0-SNAPSHOT/CreateSale");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            console.log(xhr.status);
            console.log(xhr.responseText);
        }};

    let data = `{
  "id": "${index}",
  "action": "${action}"
}`;

    xhr.send(data);

    if(action === `removeItm`)
    {
        setTimeout(()=>{window.location.href = "http://localhost:8080/demo-1.0-SNAPSHOT/CreateSale";},100 )
    }

    if(action === `decreaseQty` || action === `increaseQty`)
    {
        handleQtyChange(action,index);
    }
}

function handleQtyChange(action,index) {
    let oldQty = document.getElementsByName(`qty${index}`)[0];
    let newQty = document.createElement(`div`);
    newQty.setAttribute(`class`, `text-center`);
    newQty.setAttribute(`name`, `qty${index}`);

    let oldTotal = document.getElementsByName(`Total`)[0];
    let newTotal = document.createElement(`span`);
    newTotal.setAttribute(`name`, `Total`)
    let price = document.getElementsByTagName("b")[index];

    let qtyNr = Number(oldQty.textContent.substring(4));
    if (action === `decreaseQty`) {
        if (qtyNr > 0) {
            newQty.textContent = (`Qty:` + (qtyNr - 1));
            newTotal.textContent = (Number(oldTotal.textContent) - Number(price.textContent)).toString();
        }
    } else {
        newQty.textContent = (`Qty:` + (qtyNr + 1));
        newTotal.textContent = (Number(oldTotal.textContent) + Number(price.textContent)).toString();
    }

    if (!(qtyNr == 0 && action === `decreaseQty`)) {
    oldTotal.parentNode.replaceChild(newTotal, oldTotal);
    oldQty.parentNode.replaceChild(newQty, oldQty);
    }
}

function FinalizeSale()
{
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/demo-1.0-SNAPSHOT/EndSale");
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            console.log(xhr.status);
            console.log(xhr.responseText);
        }};

    xhr.send();
    setTimeout(()=>{window.location.href = "http://localhost:8080/demo-1.0-SNAPSHOT/CreateSale";},100 );
}
