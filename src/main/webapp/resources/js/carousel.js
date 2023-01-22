// let slide1Position = 0;
// let slide2Position = 0;
// let slide3Position = 0;
// const slides1 = document.getElementsByClassName('carousel_item');
// const slides2 = document.getElementsByClassName('carousel_item');
// // const slides3=document.getElementsByClassName('carousel3_item');
// const dots = document.getElementsByClassName('dot');
//
// let next_button = document.getElementById('carousel_button--next');
// let previous_button = document.getElementById('carousel_button--prev');
//
//
// if (next_button != null) {
//
//     next_button.addEventListener("click", function () {
//
//
//     })
// }
//
// if (previous_button != null) {
//     previous_button.addEventListener("click", function () {
//
//     })
// }

function createCarousel(carousel) {
    const slides = document.querySelectorAll(carousel + ' .carousel_item');
    const dots = document.querySelectorAll(carousel + ' .dot');
    let lastPosition = -1;
    let slidePosition = 0;


    document.querySelector(carousel +
        ' #carousel_button--prev').addEventListener('click', function () {
        moveSlide(-1);
    });

    document.querySelector(carousel +
        ' #carousel_button--next').addEventListener('click', function () {
        moveSlide(1);
    });

    dots.forEach(function (dot) {
        dot.addEventListener('click', function () {
            let bulletIndex = this.getAttribute('data-index');
            showSlideUsingBullets(bulletIndex);
        });
    });


    function changeCurrentSlide(slideNr, increment) {
        lastPosition = slideNr;
        slidePosition = (slideNr + increment + slides.length) % slides.length;
    }

    function changeSlideState() {
        slides[lastPosition].classList.remove('carousel_item--visible');
        slides[lastPosition].classList.add('carousel_item--hidden');
        slides[slidePosition].classList.add('carousel_item--visible');
        setBulletsStateInactive();
        dots[slidePosition].className += ' active';
    }

    function moveSlide(n) {
        changeCurrentSlide(slidePosition, n);
        changeSlideState();
    }

    // function moveToPrevSlide(n) {
    //     changeCurrentSlide(slidePosition, n);
    //     changeSlideState(); //refactor
    // }

    function showSlideUsingBullets(n) {
        if(n==slidePosition) return;
        moveSlide(n-slidePosition);
       /* if (n > slidePosition) {
            moveSlide(n - slidePosition);
        } else if (n < slidePosition) {
            moveSlide(n - slidePosition);
        }*/

    }

    function setBulletsStateInactive() {
        let i;
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
    }
}

// function changeSlideState(slide, slidePos) {
//     slide.classList.remove('carousel_item--visible');
//     slide.classList.add('carousel_item--hidden');
//     slide[slidePos].classList.add('carousel_item--visible');
//     // setBulletsStateInactive();
//     dots[slidePos].className += " active";
// }
//
// function moveToNextSlide(carousel, slidePos) {
//     const slides = document.querySelectorAll(carousel + ' .carousel_item');
//     setSlidePositionToNext(slides[slidePos], slidePos);
//     changeSlideState(nrSlide, slidePos);
// }
//
// function moveToPrevSlide(nrSlide, slidePos) {
//
//     setSlidePositionToPrev(nrSlide, slidePos);
//     changeSlideState(nrSlide, slidePos);
// }
//
//
//
// function setSlidePositionToNext(nrSlide, slidePos) {
//
//     if (slidePos === nrSlide.length - 1) {
//         slidePos = 0;
//     } else {
//         slidePos++;
//     }
// }
//
// function setSlidePositionToPrev(nrSlide, slidePos) {
//     if (slidePos === 0) {
//         slidePos = nrSlide.length - 1;
//     } else {
//         slidePos--;
//     }
// }

createCarousel('#carousel1');
createCarousel('#carousel2');
createCarousel('#carousel3');