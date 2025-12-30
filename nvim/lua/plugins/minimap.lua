return {
  {
    "echasnovski/mini.nvim",
    version = false,
    config = function()
      local map = require("mini.map")

      map.setup({
        -- Prickar = mer VSCode-känsla och snabb rendering
        symbols = { encode = map.gen_encode_symbols.dot("4x2") },

        -- Gör den till en smal “strip” som VSCode
        window = {
          side = "right",
          width = 8, -- prova 6–10
          winblend = 25, -- lite transparent
          show_integration_count = false,
        },
      })

      -- Starta automatiskt
      vim.api.nvim_create_autocmd("VimEnter", {
        callback = function()
          map.open()
        end,
      })

      -- Toggle (valfritt)
      vim.keymap.set("n", "<leader>um", function()
        if map.is_open() then
          map.close()
        else
          map.open()
        end
      end, { desc = "Toggle minimap" })
    end,
  },
}
