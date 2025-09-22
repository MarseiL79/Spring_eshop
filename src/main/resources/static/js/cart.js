// cart.js
document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('add-to-cart-form');
    if (!form) return;

    const button = form.querySelector('button[type="submit"]');
    const cartCountElem = document.getElementById('cart-count');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const productId = form.querySelector('input[name="productId"]').value;
        const quantity = form.querySelector('input[name="quantity"]')?.value || 1;

        const payload = { productId: Number(productId), quantity: Number(quantity) };

        const csrfToken = document.querySelector('meta[name="_csrf"]')?.content;
        const csrfHeader = document.querySelector('meta[name="_csrf_header"]')?.content;

        const headers = { 'Content-Type': 'application/json' };
        if (csrfToken && csrfHeader) headers[csrfHeader] = csrfToken;

        try {
            const resp = await fetch('/api/cart/add', {
                method: 'POST',
                headers,
                body: JSON.stringify(payload)
            });

            if (!resp.ok) {
                const body = await resp.text();
                console.error('Ошибка сервера', resp.status, body);
                alert('Ошибка при добавлении в корзину: ' + resp.status);
                return;
            }

            if (cartCountElem) {
                let current = parseInt(cartCountElem.textContent) || 0;
                cartCountElem.textContent = current + Number(quantity);
            }

            const buttonTextElem = button.querySelector('.add-button-text');

            if (buttonTextElem) {
                const originalText = buttonTextElem.textContent;
                buttonTextElem.textContent = 'ДОБАВЛЕНО';
                button.disabled = true;
                setTimeout(() => {
                    buttonTextElem.textContent = originalText;
                    button.disabled = false;
                }, 2000);
            }

        } catch (err) {
            console.error('Сетевая ошибка', err);
            alert('Сетевая ошибка при добавлении в корзину');
        }
    });
});
