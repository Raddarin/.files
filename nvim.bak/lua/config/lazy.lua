return {
  "hrsh7th/nvim-cmp",
  opts = function(_, opts)
    local cmp = require("cmp")

    -- Sätt egna mappings så vi styr Enter & Tab
    opts.mapping = cmp.mapping.preset.insert({
      -- ENTER: vanlig enter, INTE confirm
      ["<CR>"] = cmp.mapping(function(fallback)
        fallback()
      end, { "i", "s" }),

      -- TAB: bekräfta/autocompleta om menyn är öppen, annars vanlig tab
      ["<Tab>"] = cmp.mapping(function(fallback)
        if cmp.visible() then
          cmp.confirm({ select = true }) -- <-- AUTOCOMPLETE HÄR
        else
          fallback()
        end
      end, { "i", "s" }),

      -- Shift-Tab: gå uppåt i listan om menyn är öppen
      ["<S-Tab>"] = cmp.mapping(function(fallback)
        if cmp.visible() then
          cmp.select_prev_item()
        else
          fallback()
        end
      end, { "i", "s" }),
    })
  end,
}
