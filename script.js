// Mobile Navigation Toggle
const serviceData = {
    residential: {
        title: 'Residential Solar',
        image: 'https://images.unsplash.com/photo-1497440001374-f26997328c1b?auto=format&fit=crop&w=1200&q=80',
        description: 'Power your home with clean, renewable energy. Our residential solar systems are designed to reduce your electricity bills by up to 80% while adding value to your property.',
        features: [
            '1 kW - 10 kW+ systems',
            '30-year warranty',
            'Government subsidies available',
            'Free site assessment'
        ],
        cta: 'Get Your Free Quote'
    },
    commercial: {
        title: 'Commercial Solar',
        image: 'https://images.unsplash.com/photo-1497435334941-8c899ee9e8e9?auto=format&fit=crop&w=1200&q=80',
        description: 'Boost your business efficiency with commercial solar installations. Reduce operational costs and improve your bottom line with customized energy solutions.',
        features: [
            '10 kW - 500 kW+ capacity',
            'Quick ROI',
            'Customized design',
            'Maintenance support'
        ],
        cta: 'Get Your Free Quote'
    },
    industrial: {
        title: 'Industrial Solar',
        image: 'https://images.unsplash.com/photo-1473341304170-971dccb5ac1e?auto=format&fit=crop&w=1200&q=80',
        description: 'Large-scale solar systems for industrial plants, warehouses, and factories that need reliable, cost-saving energy for continuous operations.',
        features: [
            'Large capacity installations',
            'Peak demand reduction',
            'Round-the-clock monitoring',
            'High output engineering'
        ],
        cta: 'Request Industrial Consultation'
    },
    'water-heaters': {
        title: 'Solar Water Heaters',
        image: 'https://images.unsplash.com/photo-1568605114967-8130f3a36994?auto=format&fit=crop&w=1200&q=80',
        description: 'Enjoy hot water 365 days a year with minimal operational costs. Our solar water heating systems are perfect for homes, hostels, apartments, and commercial properties.',
        features: [
            'Energy-efficient design',
            'Low maintenance',
            '10-year warranty',
            'Instant installation'
        ],
        cta: 'Ask About Solar Heaters'
    },
    pumps: {
        title: 'Solar Water Pumps',
        image: 'https://images.unsplash.com/photo-1501004318641-b39e6451bec6?auto=format&fit=crop&w=1200&q=80',
        description: 'Ideal for agriculture and farming. Solar water pumps eliminate fuel dependence and reduce irrigation costs with reliable day-long performance.',
        features: [
            'Zero fuel cost',
            'Low maintenance',
            'Durable construction',
            'Agricultural subsidies available'
        ],
        cta: 'Get Pump System Quote'
    }
};

const productData = {
    tata: {
        title: 'Tata Solar Panels',
        image: 'https://images.unsplash.com/photo-1509391366360-2e959784a276?auto=format&fit=crop&w=1200&q=80',
        description: 'Tata Power Solar is one of India’s largest and most trusted solar panel manufacturers with decades of experience and dependable system performance.',
        features: [
            'Polycrystalline & Monocrystalline options',
            '19-22% efficiency',
            '25-year performance warranty',
            'Reliable temperature coefficients'
        ],
        cta: 'Get Tata Solar Quote'
    },
    waree: {
        title: 'Waree Energies',
        image: 'https://images.unsplash.com/photo-1473341304170-971dccb5ac1e?auto=format&fit=crop&w=1200&q=80',
        description: 'Waree Energies is a leading innovator in solar technology with state-of-the-art manufacturing facilities and strong performance across residential and commercial applications.',
        features: [
            'Bifacial & monofacial technology',
            'Up to 22% efficiency',
            '30-year warranty available',
            'Made in India, globally certified'
        ],
        cta: 'Get Waree Solar Quote'
    },
    renewsys: {
        title: 'RenewSys Solar',
        image: 'https://images.unsplash.com/photo-1497435334941-8c899ee9e8e9?auto=format&fit=crop&w=1200&q=80',
        description: 'RenewSys offers innovative, high-performance solar modules with advanced manufacturing technology built for long-term energy reliability.',
        features: [
            'High efficiency: 20-22%',
            'Low temperature coefficient',
            '25-year power warranty',
            'Advanced reliability testing'
        ],
        cta: 'Get RenewSys Quote'
    },
    adani: {
        title: 'Adani Solar',
        image: 'https://images.unsplash.com/photo-1466611653911-95081537e5b7?auto=format&fit=crop&w=1200&q=80',
        description: 'Adani Solar is India’s largest solar PV manufacturer with world-class products, dependable design, and strong performance for large-scale solutions.',
        features: [
            'Efficiency: 20-22%',
            'Bifacial & monofacial modules',
            '25-year comprehensive warranty',
            'High reliability performance'
        ],
        cta: 'Get Adani Solar Quote'
    },
    inverters: {
        title: 'Solar Inverters',
        image: 'https://images.unsplash.com/photo-1518770660439-4636190af475?auto=format&fit=crop&w=1200&q=80',
        description: 'Smart inverter systems for converting DC power into usable AC power, with options for grid-tie, off-grid, and hybrid setups.',
        features: [
            'String, hybrid, and grid-tie options',
            'High energy efficiency',
            'Smart monitoring support',
            'Battery compatibility'
        ],
        cta: 'Get Inverter Solution'
    },
    batteries: {
        title: 'Solar Battery Systems',
        image: 'https://images.unsplash.com/photo-1553413077-190dd305871c?auto=format&fit=crop&w=1200&q=80',
        description: 'Store solar energy for backup power and peak-hour usage with advanced battery storage designed for homes, businesses, and industrial setups.',
        features: [
            'Lithium-ion and lead-acid options',
            'Long backup duration',
            'Monitoring and safety management',
            'Suitable for hybrid systems'
        ],
        cta: 'Get Battery Backup Quote'
    }
};

function renderServiceDetail() {
    const detailHost = document.getElementById('serviceDetail');
    if (!detailHost) return;

    const params = new URLSearchParams(window.location.search);
    const type = params.get('type') || 'residential';
    const item = serviceData[type] || serviceData.residential;

    const featureMarkup = item.features.map(feature => `
        <li style="padding: 10px 0;"><i class="fas fa-check" style="color: var(--primary-color); margin-right: 10px;"></i>${feature}</li>
    `).join('');

    detailHost.innerHTML = `
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 40px; align-items: center; margin-bottom: 40px;">
            <div>
                <h3 style="font-size: 28px; margin-bottom: 20px;">${item.title}</h3>
                <p>${item.description}</p>
                <ul style="list-style: none; margin-top: 20px;">${featureMarkup}</ul>
                <a href="contact.html" class="btn btn-primary" style="margin-top: 20px;">${item.cta}</a>
            </div>
            <div class="service-visual" style="background-image: url('${item.image}'); height: 320px;"></div>
        </div>
    `;
}

function renderProductDetail() {
    const detailHost = document.getElementById('productDetail');
    if (!detailHost) return;

    const params = new URLSearchParams(window.location.search);
    const type = params.get('brand') || params.get('type') || 'tata';
    const item = productData[type] || productData.tata;

    const featureMarkup = item.features.map(feature => `
        <li style="padding: 10px 0;"><i class="fas fa-check" style="color: var(--primary-color); margin-right: 10px;"></i>${feature}</li>
    `).join('');

    detailHost.innerHTML = `
        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 40px; align-items: center; margin-bottom: 40px;">
            <div class="product-visual" style="background-image: url('${item.image}'); height: 320px;"></div>
            <div>
                <h3 style="font-size: 28px; margin-bottom: 20px;">${item.title}</h3>
                <p>${item.description}</p>
                <ul style="list-style: none; margin-top: 20px;">${featureMarkup}</ul>
                <a href="contact.html" class="btn btn-primary" style="margin-top: 20px;">${item.cta}</a>
            </div>
        </div>
    `;
}

function initHeroCarousel() {
    const slides = document.querySelectorAll('.hero-slide');
    if (!slides.length) return;

    let activeIndex = 0;
    setInterval(() => {
        slides[activeIndex].classList.remove('active');
        activeIndex = (activeIndex + 1) % slides.length;
        slides[activeIndex].classList.add('active');
    }, 3500);
}

function initPhotoCarousel() {
    const carousel = document.querySelector('[data-carousel]');
    if (!carousel) return;

    const track = carousel.querySelector('.photo-track');
    const cards = carousel.querySelectorAll('.photo-card');
    const previousButton = document.querySelector('[data-carousel-prev]');
    const nextButton = document.querySelector('[data-carousel-next]');
    let currentIndex = 0;

    const getVisibleCards = () => window.innerWidth <= 768 ? 1 : 2;
    const move = (direction) => {
        const visibleCards = getVisibleCards();
        const maxIndex = Math.max(0, cards.length - visibleCards);
        currentIndex = Math.min(maxIndex, Math.max(0, currentIndex + direction));
        const cardWidth = cards[0].getBoundingClientRect().width + 18;
        track.style.transform = `translateX(-${currentIndex * cardWidth}px)`;
    };

    previousButton?.addEventListener('click', () => move(-1));
    nextButton?.addEventListener('click', () => move(1));
    window.addEventListener('resize', () => move(0));
    setInterval(() => {
        const visibleCards = getVisibleCards();
        currentIndex = currentIndex >= cards.length - visibleCards ? 0 : currentIndex + 1;
        move(0);
    }, 4000);
}

document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.getElementById('menuToggle');
    const navMenu = document.getElementById('navMenu');
    
    initHeroCarousel();
    initPhotoCarousel();
    renderServiceDetail();
    renderProductDetail();

    // Toggle menu on button click
    if (menuToggle) {
        menuToggle.addEventListener('click', function() {
            navMenu.classList.toggle('active');
            menuToggle.classList.toggle('active');
        });
    }
    
    // Close menu when clicking on a link
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            // Only close if it's not a dropdown toggle
            if (!this.classList.contains('dropdown-toggle')) {
                navMenu.classList.remove('active');
                menuToggle.classList.remove('active');
            }
        });
    });
    
    // Close menu when clicking outside
    document.addEventListener('click', function(e) {
        if (!e.target.closest('.navbar')) {
            navMenu.classList.remove('active');
            menuToggle.classList.remove('active');
        }
    });
    
    // Handle dropdown toggles on mobile
    const dropdownToggles = document.querySelectorAll('.dropdown-toggle');
    dropdownToggles.forEach(toggle => {
        toggle.addEventListener('click', function(e) {
            if (window.innerWidth <= 768) {
                e.preventDefault();
                const dropdownMenu = this.nextElementSibling;
                if (dropdownMenu && dropdownMenu.classList.contains('dropdown-menu')) {
                    dropdownMenu.style.display = 
                        dropdownMenu.style.display === 'block' ? 'none' : 'block';
                }
            }
        });
    });
    
    // Highlight active page in navigation
    const currentPage = window.location.pathname.split('/').pop() || 'index.html';
    const navItems = document.querySelectorAll('.nav-link');
    navItems.forEach(item => {
        if (item.getAttribute('href') === currentPage || 
            (currentPage === '' && item.getAttribute('href') === 'index.html')) {
            item.classList.add('active');
        }
    });
});

// Smooth scroll behavior
function smoothScroll(target) {
    const element = document.querySelector(target);
    if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
    }
}

// Fade in animation on scroll
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -100px 0px'
};

const observer = new IntersectionObserver(function(entries) {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
            observer.unobserve(entry.target);
        }
    });
}, observerOptions);

// Apply fade in to cards
document.addEventListener('DOMContentLoaded', function() {
    const cards = document.querySelectorAll(
        '.feature-card, .brand-item, .solution-card, .financing-card, .faq-item, .blog-card'
    );
    
    cards.forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';
        card.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        observer.observe(card);
    });
});

// Scroll to top button
window.addEventListener('scroll', function() {
    if (document.documentElement.scrollTop > 300) {
        if (!document.getElementById('scrollTopBtn')) {
            const btn = document.createElement('button');
            btn.id = 'scrollTopBtn';
            btn.innerHTML = '↑';
            btn.style.cssText = `
                position: fixed;
                bottom: 30px;
                right: 30px;
                background: var(--primary-color);
                color: white;
                border: none;
                border-radius: 50%;
                width: 45px;
                height: 45px;
                font-size: 24px;
                cursor: pointer;
                display: flex;
                align-items: center;
                justify-content: center;
                box-shadow: 0 4px 12px rgba(0,0,0,0.15);
                z-index: 999;
                transition: all 0.3s ease;
            `;
            btn.onmouseover = () => {
                btn.style.background = 'var(--secondary-color)';
                btn.style.transform = 'translateY(-3px)';
            };
            btn.onmouseout = () => {
                btn.style.background = 'var(--primary-color)';
                btn.style.transform = 'translateY(0)';
            };
            btn.onclick = () => {
                window.scrollTo({ top: 0, behavior: 'smooth' });
            };
            document.body.appendChild(btn);
        }
    } else {
        const btn = document.getElementById('scrollTopBtn');
        if (btn) btn.remove();
    }
});

// Form handling
function handleFormSubmit(e) {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);
    const data = Object.fromEntries(formData);

    if (!data.name || !data.email || !data.phone) {
        alert('Please fill in all required fields');
        return;
    }

    const submitButton = form.querySelector('button[type="submit"]');
    submitButton.disabled = true;
    submitButton.textContent = 'Sending...';

    const apiUrl = window.location.port === '8000'
        ? `${window.location.protocol}//${window.location.hostname}:8001/api/enquiries`
        : '/api/enquiries';

    fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
        .then(async response => {
            const contentType = response.headers.get('content-type') || '';
            if (!contentType.includes('application/json')) {
                throw new Error('The enquiry server is not running. Please start the Node server and try again.');
            }
            const result = await response.json();
            if (!response.ok) throw new Error(result.error || 'Unable to send enquiry.');
            return result;
        })
        .then(() => {
            alert('Thank you! We will contact you soon.');
            form.reset();
        })
        .catch(error => {
            alert(error.message || 'Unable to send enquiry. Please try again.');
        })
        .finally(() => {
            submitButton.disabled = false;
            submitButton.textContent = 'Send Message';
        });
}

// Counter animation for stats
function animateCounter(element, target, duration = 2000) {
    const start = 0;
    const increment = target / (duration / 16);
    let current = start;
    
    const timer = setInterval(() => {
        current += increment;
        if (current >= target) {
            element.textContent = target;
            clearInterval(timer);
        } else {
            element.textContent = Math.floor(current);
        }
    }, 16);
}

// Animate counters when stats section is in view
document.addEventListener('DOMContentLoaded', function() {
    const statsSection = document.querySelector('.stats');
    if (statsSection) {
        const observer = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const statNumbers = document.querySelectorAll('.stat-number');
                    statNumbers.forEach(stat => {
                        const text = stat.textContent;
                        const number = parseInt(text.replace(/\D/g, ''));
                        if (!isNaN(number)) {
                            animateCounter(stat, number);
                        }
                    });
                    observer.unobserve(entry.target);
                }
            });
        }, { threshold: 0.5 });
        
        observer.observe(statsSection);
    }
});
