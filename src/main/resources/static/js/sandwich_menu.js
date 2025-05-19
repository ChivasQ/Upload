const toggle = document.querySelector('.nav__button');
const links = document.querySelector('.nav__links');

toggle.addEventListener('click', () => {
    links.classList.toggle('show');
});