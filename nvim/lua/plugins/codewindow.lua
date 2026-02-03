return {
  "gorbit99/codewindow.nvim",
  enabled = false, -- Detta stänger av pluginet helt
  config = function()
    local codewindow = require("codewindow")
    codewindow.setup()
    codewindow.apply_default_keybinds()
  end,
}
