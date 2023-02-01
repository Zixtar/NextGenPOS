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
}
