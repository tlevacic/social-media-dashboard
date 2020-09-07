const defaultTheme = require('tailwindcss/defaultTheme')
module.exports = {
  future: {
    removeDeprecatedGapUtilities: true,
  },
  theme: {
    extend:{
      colors:{
        'lime-green':'hsl(163, 72%, 41%)',
        'bright-red':'hsl(356, 69%, 56%)',
        'facebook': 'hsl(208, 92%, 53%)',
        'twitter':'hsl(203, 89%, 53%)',
        'instagram': 'linear-gradient(hsl(37, 97%, 70%),to hsl(329, 70%, 58%))',
        'youtube':'hsl(348, 97%, 39%)',
        'l-white':'hsl(0, 0%, 100%)',
        'very-pale-blue':'hsl(225, 100%, 98%)',
        'light-grayish-blue':'hsl(227, 47%, 96%)',
        'dark-grayish-blue':'hsl(228, 12%, 44%)',
        'very-dark-blue':'hsl(230, 17%, 14%)',
        'toggle':'linear-gradient(hsl(210, 78%, 56%),to hsl(146, 68%, 55%))',
        'card-bg-dark':'hsl(228, 28%, 20%)',
      }
    }
  }
};