const menuToggle = document.querySelector('.menu-toggle');
const mainNav = document.querySelector('.main-nav');
const modal = document.querySelector('#quote-modal');
const quoteTriggers = document.querySelectorAll('.quote-trigger');
const modalClose = document.querySelector('.modal-close');
const carousel = document.querySelector('#solar-carousel');
const carouselSlides = [...document.querySelectorAll('.hero-slide')];
const carouselDots = document.querySelector('.carousel-dots');
const carouselCount = document.querySelector('.carousel-count');
const carouselTitle = document.querySelector('.carousel-title');
let activeSlide = 0;
let carouselTimer;

const showSlide = (nextSlide) => {
  activeSlide = (nextSlide + carouselSlides.length) % carouselSlides.length;
  carouselSlides.forEach((slide, index) => slide.classList.toggle('active', index === activeSlide));
  document.querySelectorAll('.carousel-dot').forEach((dot, index) => {
    dot.classList.toggle('active', index === activeSlide);
    dot.setAttribute('aria-current', index === activeSlide ? 'true' : 'false');
  });
  carouselCount.textContent = `${String(activeSlide + 1).padStart(2, '0')} / ${String(carouselSlides.length).padStart(2, '0')}`;
  carouselTitle.textContent = carouselSlides[activeSlide].dataset.title;
};

carouselSlides.forEach((_, index) => {
  const dot = document.createElement('button');
  dot.className = 'carousel-dot';
  dot.type = 'button';
  dot.setAttribute('aria-label', `Show solar image ${index + 1}`);
  dot.addEventListener('click', () => showSlide(index));
  carouselDots.append(dot);
});
showSlide(0);

const resetCarouselTimer = () => {
  clearInterval(carouselTimer);
  carouselTimer = setInterval(() => showSlide(activeSlide + 1), 5000);
};

document.querySelector('.carousel-prev')?.addEventListener('click', () => { showSlide(activeSlide - 1); resetCarouselTimer(); });
document.querySelector('.carousel-next')?.addEventListener('click', () => { showSlide(activeSlide + 1); resetCarouselTimer(); });
carousel?.addEventListener('mouseenter', () => clearInterval(carouselTimer));
carousel?.addEventListener('mouseleave', resetCarouselTimer);
carousel?.addEventListener('focusin', () => clearInterval(carouselTimer));
carousel?.addEventListener('focusout', resetCarouselTimer);
carousel?.addEventListener('keydown', (event) => {
  if (event.key === 'ArrowLeft') { showSlide(activeSlide - 1); resetCarouselTimer(); }
  if (event.key === 'ArrowRight') { showSlide(activeSlide + 1); resetCarouselTimer(); }
});
resetCarouselTimer();

menuToggle?.addEventListener('click', () => {
  const isOpen = mainNav.classList.toggle('open');
  menuToggle.setAttribute('aria-expanded', String(isOpen));
});

document.querySelectorAll('.main-nav a').forEach((link) => {
  link.addEventListener('click', () => {
    mainNav.classList.remove('open');
    menuToggle.setAttribute('aria-expanded', 'false');
  });
});

const setModal = (isOpen) => {
  modal.classList.toggle('open', isOpen);
  modal.setAttribute('aria-hidden', String(!isOpen));
  document.body.style.overflow = isOpen ? 'hidden' : '';
};

quoteTriggers.forEach((trigger) => trigger.addEventListener('click', () => setModal(true)));
modalClose?.addEventListener('click', () => setModal(false));
modal?.addEventListener('click', (event) => {
  if (event.target === modal) setModal(false);
});
document.addEventListener('keydown', (event) => {
  if (event.key === 'Escape') setModal(false);
});

document.querySelector('#quote-form')?.addEventListener('submit', async (event) => {
  event.preventDefault();
  const status = document.querySelector('.form-status');
  const submitButton = event.target.querySelector('button[type="submit"]');
  submitButton.disabled = true;
  status.textContent = 'Sending your enquiry...';

  try {
    const response = await fetch('/api/enquiries', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(Object.fromEntries(new FormData(event.target)))
    });

    if (!response.ok) throw new Error('Unable to save enquiry');
    status.textContent = 'Thank you. Our solar team will contact you shortly.';
    event.target.reset();
  } catch (error) {
    status.textContent = 'We could not save your enquiry. Please call 83093 23693.';
  } finally {
    submitButton.disabled = false;
  }
});

document.querySelector('#year').textContent = new Date().getFullYear();

// FAQ Accordion functionality
document.querySelectorAll('.faq-toggle').forEach((toggle) => {
  toggle.addEventListener('click', () => {
    const isExpanded = toggle.getAttribute('aria-expanded') === 'true';
    const content = toggle.nextElementSibling;
    
    toggle.setAttribute('aria-expanded', String(!isExpanded));
    content.hidden = isExpanded;
    
    // Close other open FAQs
    document.querySelectorAll('.faq-toggle').forEach((otherToggle) => {
      if (otherToggle !== toggle) {
        otherToggle.setAttribute('aria-expanded', 'false');
        otherToggle.nextElementSibling.hidden = true;
      }
    });
  });
});
