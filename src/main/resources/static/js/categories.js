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
                // 4) Запрашиваем REST‑API для получения продуктов по категории
                //    предполагаем, что у вас есть GET /api/categories/{id}/products
                const resp = await fetch(`/api/categories/${categoryId}/products`);
                if (!resp.ok) throw new Error(`HTTP ${resp.status}`);
                const products = await resp.json();

                // 5) Строим HTML-карточки продуктов и вставляем в контейнер
                productContainer.innerHTML = products.map(p => `
          <div class="card">
            <img class="card__img" src="${p.imageUrl || '/img/tulen.jpg'}" alt="${p.name}">
            <div class="card-text">
              <h4 class="card-title">${p.name}</h4>
              <p>${p.description || ''}</p>
              <strong class="card-price">${p.price}₽</strong>
            </div>
          </div>
        `).join('');

            } catch (e) {
                console.error(e);
                productContainer.innerHTML = `<p class="error">Не удалось загрузить товары.</p>`;
            }
        });
    });
});
