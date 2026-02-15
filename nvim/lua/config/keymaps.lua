-- Keymaps are automatically loaded on the VeryLazy event
-- Default keymaps that are always set: https://github.com/LazyVim/LazyVim/blob/main/lua/lazyvim/config/keymaps.lua
-- Add any additional keymaps here
--

local map = vim.keymap.set

-- Insert mode: "jk" => Esc
map("i", "jk", "<Esc>", { desc = "Exit insert mode", noremap = true, silent = true })

-- Om du hellre vill ha "kj":
map("i", "kj", "<Esc>", { desc = "Exit insert mode", noremap = true, silent = true })

-- Våga vägra notebooks - Skicka markerad kod till tmux (höger ruta)
map("v", "<leader>r", function()
  -- Kopiera markeringen till register 'v'
  vim.cmd([[normal! "vy]])
  local text = vim.fn.getreg("v")

  -- Bracketed paste-koder för att IPython inte ska få fnatt på indrag/new-lines
  local begin_paste = [[ $'\033[200~' ]]
  local end_paste = [[ $'\033[201~' ]]

  -- ":.+" betyder "fokusera på nästa ruta i tmux" (oftast den till höger/under)
  local target = ":.+"

  -- Bygg kommandot och skicka till tmux
  local cmd = "tmux send-keys -t " .. target .. begin_paste .. vim.fn.shellescape(text) .. end_paste .. " Enter"

  os.execute(cmd)
end, { desc = "Send selection to IPython (tmux right pane)" })
