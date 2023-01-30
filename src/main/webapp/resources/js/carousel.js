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
        setBulletsStateInactive(dots);
        dots[slidePosition].className += ' active';
    }

    function moveSlide(n) {
        changeCurrentSlide(slidePosition, n);
        changeSlideState();
    }

    function showSlideUsingBullets(n) {
        if (n == slidePosition) return;
        moveSlide(n - slidePosition);
    }
}

function setBulletsStateInactive(dots) {
    let i;
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
}

/*old code
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
*/
createCarousel('#carousel1');
// createCarousel('#carousel3');


/* ---------------Carousel 2 -------------------->*/


function scrollProductCarousel(carousel) {
    const productContainers = document.querySelector(carousel+ ' .product-container');
    const nextBtn = document.querySelector(carousel + ' #carousel_button--next');
    const prevBtn = document.querySelector(carousel + ' #carousel_button--prev');
    const dots = document.querySelectorAll(carousel + ' .dot');
    const dotsLength = dots.length;
    let currentDotNr = 0;
    let containerDimensions = productContainers.getBoundingClientRect();
    let containerWidth = containerDimensions.width;
    nextBtn.addEventListener('click', () => {
        if (currentDotNr < dotsLength - 1) {
            productContainers.scrollLeft += containerWidth;
            scroll(1);
        }

    });
    prevBtn.addEventListener('click', () => {
        if (currentDotNr > 0) {
            productContainers.scrollLeft -= containerWidth;
            scroll(-1);
        }
    });

    dots.forEach(function (dot) {
        dot.addEventListener('click', function () {
            let bulletIndex = this.getAttribute('data-index');

            if (bulletIndex > currentDotNr) {
                productContainers.scrollLeft += (bulletIndex - currentDotNr) * containerWidth;
            } else if (bulletIndex < currentDotNr) {
                productContainers.scrollLeft -= (-1) * (bulletIndex - currentDotNr) * containerWidth;
            }
            showProductsUsingBullets(bulletIndex);
        });
    });


    function showProductsUsingBullets(index) {
        if (currentDotNr == index) return;
        scroll(index - currentDotNr);
    }

    function scroll(index) {
        currentDotNr = currentDotNr + index;
        setBulletsStateInactive(dots);
        dots[currentDotNr].className += ' active';
    }
}

scrollProductCarousel('#carousel2');
scrollProductCarousel('#carousel3');