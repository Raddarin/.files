-- Keymaps are automatically loaded on the VeryLazy event
-- Default keymaps that are always set: https://github.com/LazyVim/LazyVim/blob/main/lua/lazyvim/config/keymaps.lua
-- Add any additional keymaps here
--

local map = vim.keymap.set

-- Insert mode: "jk" => Esc
map("i", "jk", "<Esc>", { desc = "Exit insert mode", noremap = true, silent = true })

-- Om du hellre vill ha "kj":
map("i", "kj", "<Esc>", { desc = "Exit insert mode", noremap = true, silent = true })
