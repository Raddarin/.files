return {
  {
    "gorbit99/codewindow.nvim",
    config = function()
      local cw = require("codewindow")
      cw.setup({
        auto_enable = false,
        width_multiplier = 4,
        minimap_width = 12,
      })

      vim.keymap.set("n", "<leader>um", function()
        cw.toggle_minimap()
      end, { desc = "Toggle minimap" })
    end,
  },
}
