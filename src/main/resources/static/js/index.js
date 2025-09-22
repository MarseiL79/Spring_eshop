document.addEventListener('DOMContentLoaded', function() {
    const carousel = document.querySelector('.horizontal-product-scroll');
    const prevBtn = document.querySelector('.prev');
    const nextBtn = document.querySelector('.next');

    // Убираем функцию updateArrows, чтобы стрелки не скрывались
    prevBtn.addEventListener('click', () => {
        carousel.scrollBy({ left: -292, behavior: 'smooth' });
    });

    nextBtn.addEventListener('click', () => {
        carousel.scrollBy({ left: 292, behavior: 'smooth' });
    });
});
