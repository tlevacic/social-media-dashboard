(ns app.data
  (:require
    [app.svgs :refer [icon-up facebook-icon icon-down instagram-icon youtube-icon twitter-icon]]))

(def data
  [{
    :icon facebook-icon
    :username "@nathanf"
    :followers "1987"
    :today "12"
    :today-color "lime-green"
    :today-icon icon-up
    :border-top-color "facebook"
    }
   {
    :icon twitter-icon
    :username "@nathanf"
    :followers "1044"
    :today "99"
    :today-color "lime-green"
    :today-icon icon-up
    :border-top-color "twitter"
    }
   {
    :icon instagram-icon
    :username "@realnathanf"
    :followers "11k"
    :today "1099"
    :today-color "lime-green"
    :today-icon icon-up
    :border-top-color "instagram"
    }
   {
    :icon youtube-icon
    :username "@Nathan F."
    :followers "8239"
    :today "144"
    :today-color "bright-red"
    :today-icon icon-down
    :border-top-color "youtube"
    }])

(def small-card-data
  [{
    :title "Page Views"
    :icon facebook-icon
    :number "87"
    :arrow icon-up
    :arrow-color "lime-green"
    :arrow-number "3%"
    }
   {
    :title "Likes"
    :icon facebook-icon
    :number "52"
    :arrow icon-down
    :arrow-color "bright-red"
    :arrow-number "2%"
    }
   {
    :title "Likes"
    :icon instagram-icon
    :number "5462"
    :arrow icon-up
    :arrow-color "lime-green"
    :arrow-number "2257%"
    }
   {
    :title "Profile Views"
    :icon instagram-icon
    :number "52k"
    :arrow icon-up
    :arrow-color "lime-green"
    :arrow-number "1375%"
    }
   {
    :title "Retweets"
    :icon twitter-icon
    :number "117"
    :arrow icon-up
    :arrow-color "lime-green"
    :arrow-number "303%"
    }
   {
    :title "Likes"
    :icon twitter-icon
    :number "507"
    :arrow icon-up
    :arrow-color "lime-green"
    :arrow-number "553%"
    }
   {
    :title "Likes"
    :icon youtube-icon
    :number "107"
    :arrow icon-down
    :arrow-color "bright-red"
    :arrow-number "19%"
    }
   {
    :title "Total Views"
    :icon youtube-icon
    :number "1407"
    :arrow icon-down
    :arrow-color "bright-red"
    :arrow-number "12%"
    }])