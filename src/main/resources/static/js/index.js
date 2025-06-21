document.addEventListener('DOMContentLoaded', function() {
    const carousel = document.querySelector('.horizontal-product-scroll');
    const prevBtn = document.querySelector('.prev');
    const nextBtn = document.querySelector('.next');

    function updateArrows() {
        prevBtn.style.display = carousel.scrollLeft === 0 ? 'none' : 'flex';
        nextBtn.style.display = carousel.scrollLeft + carousel.clientWidth >= carousel.scrollWidth - 1 ? 'none' : 'flex';
    }

    prevBtn.addEventListener('click', () => {
        carousel.scrollBy({ left: -320, behavior: 'smooth' });
    });

    nextBtn.addEventListener('click', () => {
        carousel.scrollBy({ left: 320, behavior: 'smooth' });
    });

    carousel.addEventListener('scroll', updateArrows);
    updateArrows(); // Инициализация при загрузке
});