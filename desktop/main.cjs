const { app, BrowserWindow } = require("electron");
const path = require("node:path");

app.setName("SpotifX");
app.setAppUserModelId("com.snuffy.spotifx");

function createWindow() {
  const iconPath = path.join(__dirname, "assets", "app-icon.ico");
  const window = new BrowserWindow({
    width: 1180,
    height: 760,
    minWidth: 820,
    minHeight: 600,
    title: "SpotifX",
    autoHideMenuBar: true,
    icon: iconPath,
    backgroundColor: "#101010",
    webPreferences: {
      nodeIntegration: false,
      contextIsolation: true,
      sandbox: true
    }
  });

  window.loadFile(path.join(__dirname, "..", "app", "src", "main", "assets", "index.html"));
  window.webContents.setWindowOpenHandler(() => ({ action: "deny" }));
}

app.whenReady().then(() => {
  createWindow();
  app.on("activate", () => {
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") app.quit();
});