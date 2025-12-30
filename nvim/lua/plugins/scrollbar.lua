return {
  {
    "wfxr/minimap.vim",
    cmd = { "Minimap", "MinimapClose", "MinimapToggle" },
    init = function()
      -- Om minimap-binära heter annorlunda på ditt system, justera:
      -- vim.g.minimap_executable = "code-minimap"
      vim.g.minimap_width = 10
      vim.g.minimap_auto_start = 0
      vim.g.minimap_auto_start_win_enter = 0
    end,
    keys = {
      { "<leader>um", "<cmd>MinimapToggle<cr>", desc = "Toggle Minimap" },
    },
  },
}
