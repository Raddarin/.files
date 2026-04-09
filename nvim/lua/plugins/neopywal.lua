return {
  "RedsXDD/neopywal.nvim",
  name = "neopywal",
  lazy = false,
  priority = 1000,
  opts = {
    transparent_background = false,
  },
  config = function(_, opts) require("neopywal").setup(opts) end,
}
