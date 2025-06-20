document.addEventListener('DOMContentLoaded', () => {
    const items = document.querySelectorAll('.categories_list_element');
    const productContainer = document.querySelector('.categories_products_list');
    let emptyFlag = false;

    async function loadProducts(categoryId) {
        try {
            const resp = await fetch(`/api/categories/${categoryId}/products`);
            if (!resp.ok) throw new Error(`HTTP ${resp.status}`);
            const products = await resp.json();

            // отрисовка карточек
            if (products.length === 0) {
                if(emptyFlag ===true) { return; }
                emptyFlag = true;
                // если товаров нет — показываем картинку-заглушку
                productContainer.innerHTML = `
            <div class="no-products">
              <img class="no-products-image" src="/img/no-products.png" alt="Нет товаров">
              <p>В этой категории пока нет товаров</p>
            </div>
          `;
            }
            else {
                emptyFlag = false;
            productContainer.innerHTML = `
        <div class="cards-container">
          ${products.map(p => `
            <div class="card">
              <img class="card__img"
                   src="${p.imageUrl || '/img/tulen.jpg'}"
                   alt="${p.name}">
              <div class="card-text">
                <h4 class="card-title">${p.name}</h4>
                <p>${p.description || ''}</p>
                <strong class="card-price">${p.price}$</strong>
              </div>
            </div>
          `).join('')}
        </div>
      `; }
        } catch (e) {
            console.error(e);
            productContainer.innerHTML = `
        <p class="error">
          Не удалось загрузить товары для категории #${categoryId}.
        </p>
      `;
        }
    }

    // hover‑обработчики на каждую категорию
    items.forEach(li => {
        li.addEventListener('mouseenter', () => {
            if(li.classList.contains('active')) { return; }
            // сброс подсветки
            items.forEach(x => x.classList.remove('active'));
            li.classList.add('active');

            const categoryId = li.dataset.id;
            loadProducts(categoryId);
        });
    });

    // сразу подсветить первую категорию и вызвать для неё загрузку
    if (items.length > 0) {
        items[0].classList.add('active');
        const firstId = items[0].dataset.id;
        loadProducts(firstId);
    }
});