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

document.addEventListener("DOMContentLoaded", () => {
    loadPageCards();
});

const API_ENDPOINTS = {
    HOME: "http://localhost:8080/api/cards",
    PROFILE: "http://localhost:8080/api/profile/cards",
    UNIVERSITY: "http://localhost:8080/api/university/cards",
    PORTFOLIO: "http://localhost:8080/api/portfolio/cards"
};

async function loadPageCards() {
    const page = document.body.dataset.page;
    const url = API_ENDPOINTS[page];
    const list = document.getElementById("cardList");
    if (!url || !list) return;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const cards = await response.json();
        if (!Array.isArray(cards)) {
            throw new Error("API 응답이 배열 형식이 아닙니다.");
        }

        list.replaceChildren();

        if (page === "HOME") {
            renderHomeCards(list, cards);
        } else {
            renderSummaryCards(list, cards);
        }
    } catch (error) {
        console.error("카드 데이터를 불러오는 중 오류 발생:", error);
        list.replaceChildren(
            createEl("p", "text-danger", "정보를 불러오지 못했습니다. Spring Boot 서버(localhost:8080) 실행 상태를 확인해 주세요.")
        );
    }
}

// HOME 페이지: 각 하위 페이지로 이동하는 요약 카드 렌더링
function renderHomeCards(list, cards) {
    cards.forEach(card => {
        const col = document.createElement("div");
        col.className = "col-md-4";

        const anchor = document.createElement("a");
        anchor.className = "text-decoration-none text-reset";
        anchor.href = card.link || "#";

        const panel = document.createElement("div");
        panel.className = "panel d-flex flex-column justify-content-between";

        const top = document.createElement("div");
        const icon = createEl("div", "icon-box d-flex align-items-center justify-content-center", card.icon || "📌");
        const title = createEl("h3", "", card.title || "");
        const desc = createEl("p", "mb-3", card.description || "");
        top.append(icon, title, desc);

        const btn = createEl("span", "text-primary fw-bold", `${card.buttonText || "자세히 보기"} →`);

        panel.append(top, btn);
        anchor.appendChild(panel);
        col.appendChild(anchor);
        list.appendChild(col);
    });
}

// PROFILE / UNIVERSITY / PORTFOLIO 페이지: 요약 카드 렌더링 + 클릭 시 상세 모달 표시
function renderSummaryCards(list, cards) {
    const colClass = cards.length === 4 ? "col-md-6" : "col-md-4";

    cards.forEach(card => {
        const col = document.createElement("div");
        col.className = colClass;

        const panel = document.createElement("div");
        panel.className = "panel d-flex flex-column justify-content-between";
        panel.style.cursor = "pointer";

        const top = document.createElement("div");
        const icon = createEl("div", "icon-box d-flex align-items-center justify-content-center", card.icon || "📌");
        const title = createEl("h3", "", card.title || "");
        const desc = createEl("p", "mb-3", card.description || "");
        top.append(icon, title, desc);

        const bottom = document.createElement("div");
        const detailBtn = createEl("button", "btn-outline btn-sm", "상세 보기 →");
        detailBtn.type = "button";
        bottom.appendChild(detailBtn);

        panel.append(top, bottom);
        panel.addEventListener("click", () => openCardDetail(card));

        col.appendChild(panel);
        list.appendChild(col);
    });
}

function openCardDetail(card) {
    const modalEl = document.getElementById("cardDetailModal");
    if (!modalEl) return;

    document.getElementById("cardDetailIcon").textContent = card.icon || "📌";
    document.getElementById("cardDetailTitle").textContent = card.title || "";
    document.getElementById("cardDetailDesc").textContent = card.description || "";
    document.getElementById("cardDetailContent").textContent = card.content || "상세 설명이 등록되지 않았습니다.";

    const linkEl = document.getElementById("cardDetailLink");
    if (card.link) {
        linkEl.href = card.link;
        linkEl.textContent = `${card.buttonText || "바로가기"} →`;
        linkEl.classList.remove("d-none");
    } else {
        linkEl.classList.add("d-none");
    }

    const modal = bootstrap.Modal.getOrCreateInstance(modalEl);
    modal.show();
}

function createEl(tag, className, text) {
    const el = document.createElement(tag);
    if (className) el.className = className;
    if (text !== undefined && text !== null) el.textContent = text;
    return el;
}
