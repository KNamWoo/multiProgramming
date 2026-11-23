// Scroll reveal animations
// 스크롤 시 콘텐츠가 부드럽게 나타나도록 처리
document.addEventListener('DOMContentLoaded', () => {
    const items = document.querySelectorAll('.panel, .section-title, .section-desc, .hero .eyebrow, .hero h1, .hero .lead-copy, .hero .btn-main, .hero .btn-outline');
    items.forEach((item, index) => {
        item.classList.add('reveal');
        if (index % 4 === 1) item.classList.add('reveal-delay-1');
        if (index % 4 === 2) item.classList.add('reveal-delay-2');
        if (index % 4 === 3) item.classList.add('reveal-delay-3');
    });
    if ('IntersectionObserver' in window) {
        const observer = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('is-visible');
                    observer.unobserve(entry.target);
                }
            });
        }, {threshold: 0.12});
        items.forEach(item => observer.observe(item));
    } else {
        items.forEach(item => item.classList.add('is-visible'));
    }
});
