// src/main/resources/static/js/categories.js

document.addEventListener('DOMContentLoaded', () => {
    const items = document.querySelectorAll('.categories_list_element');
    const productContainer = document.querySelector('.categories_products_list');

    items.forEach(li => {
        li.addEventListener('mouseenter', async () => {
            // Переключаем подсветку
            items.forEach(x => x.classList.remove('active'));
            li.classList.add('active');

            // Загрузить под‑категории (пример API)
            const id = li.dataset.id;
            try {
                const resp = await fetch(`/api/categories/${id}/subcategories`);
                const subs = await resp.json();
                // Отрисовать их справа
                productContainer.innerHTML = subs.map(s =>
                    `<div class="subcategory">
             <a href="/categories/${id}/products?sub=${s.id}">${s.name}</a>
           </div>`
                ).join('');
            } catch (e) {
                productContainer.innerHTML = '<p>Ошибка загрузки.</p>';
                console.error(e);
            }
        });
    });
});
