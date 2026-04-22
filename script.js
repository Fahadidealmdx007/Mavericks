// Replace these placeholder URLs with your real product images
// (e.g. "hoodie-black.png", "hoodie-red.png", "hoodie-green.png")
// once you have them in the same folder as index.html.
const hoodieVariants = [
  {
    name: "Onyx",
    img: "https://placehold.co/400x500/111111/ffffff?text=Black+Hoodie",
    bg: "radial-gradient(circle at top, #222, #000)"
  },
  {
    name: "Crimson",
    img: "https://placehold.co/400x500/b02a2a/ffffff?text=Red+Hoodie",
    bg: "radial-gradient(circle at top, #4a0000, #100000)"
  },
  {
    name: "Emerald",
    img: "https://placehold.co/400x500/0b6b5a/ffffff?text=Green+Hoodie",
    bg: "radial-gradient(circle at top, #004534, #001410)"
  }
];

const hoodieImg = document.getElementById("hoodie-image");
const hoodieBg = document.getElementById("hoodie-bg");
const dots = document.querySelectorAll(".color-dot");

function setVariant(index) {
  const variant = hoodieVariants[index];
  if (!variant) return;

  hoodieImg.style.opacity = "0";
  hoodieImg.style.transform = "translateY(6px) scale(0.995)";

  setTimeout(() => {
    hoodieImg.src = variant.img;
    hoodieImg.alt = "Mavericks Hoodie - " + variant.name;
    hoodieBg.style.background = variant.bg;

    hoodieImg.style.opacity = "1";
    hoodieImg.style.transform = "translateY(0) scale(1)";
  }, 200);

  dots.forEach((d) => d.classList.remove("active"));
  if (dots[index]) dots[index].classList.add("active");
}

dots.forEach((dot) => {
  dot.addEventListener("click", () => {
    const i = parseInt(dot.getAttribute("data-variant"), 10);
    setVariant(i);
  });
});

// Auto-cycle colors like Apple
let current = 0;
setInterval(() => {
  current = (current + 1) % hoodieVariants.length;
  setVariant(current);
}, 4000);