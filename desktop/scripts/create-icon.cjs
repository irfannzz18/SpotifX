const fs = require("node:fs");
const path = require("node:path");
const sharp = require("sharp");
const pngToIco = require("png-to-ico");

async function main() {
  const assets = path.join(__dirname, "..", "assets");
  const svg = fs.readFileSync(path.join(assets, "app-icon.svg"));
  const sizes = [256, 128, 64, 48, 32, 16];
  const pngs = await Promise.all(
    sizes.map((size) => sharp(svg).resize(size, size).png().toBuffer())
  );
  const ico = await pngToIco(pngs);
  fs.writeFileSync(path.join(assets, "app-icon.ico"), ico);
}

main().catch((error) => {
  console.error("Could not create the SpotifX Windows icon:", error);
  process.exit(1);
});