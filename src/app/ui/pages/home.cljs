(ns app.ui.pages.home
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [helix.hooks :as hooks :refer [use-state]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.lib :refer [defnc]]
            [app.data :refer [data small-card-data]]
            [app.svgs :refer [icon-up facebook-icon icon-down instagram-icon youtube-icon twitter-icon]]
            [keechma.next.helix.classified :refer [defclassified]]))

(def dark-theme-mode (atom false))

(defn change-theme-mode [value]
  (reset! dark-theme-mode value))

(defclassified HomepageWrapper :div "h-screen w-screen flex"
               (fn [props]
                 (let [_ (println (:style props))]
                   " "))
               (fn [{:keys [darkmode?]}] (if darkmode? "bg-very-dark-blue" "bg-gray-200")))

(defclassified InnerWrapper :div "w-full sm:w-10/12 mx-auto p-8"
               (fn [{:keys [darkmode?]}] (if darkmode? "bg-very-dark-blue" "bg-white")))

(defclassified CardWrapper :div "flex flex-col justify-center items-center p-8 w-48 h-48 rounded-md border-t-2"
               (fn [{:keys [darkmode?]}] (if darkmode? " bg-card-bg-dark " " bg-gray-200 "))
               (fn [{:keys [border-top-color]}] (str "border-" border-top-color)))

(defnc TitleRenderer [{:keys [darkmode?]}]
       (d/div
         (d/p {:class (str "text-2xl font-medium" (if darkmode? " text-white " " text-black "))} "Social Media Dashboard")
         (d/p {:class "text-sm text-gray-700"} "Total Followers: 23,004")))

(defnc CheckBox [props]
       (let [[checked setChecked] (use-state false)]
         (d/div {:class "relative w-10 h-5"}
                (d/input
                  {:type     "checkbox"
                   :id       "checkbox"
                   :class    "absolute top-0 left-0 invisible"
                   :on-click (fn []
                               (setChecked (not checked))
                               (dispatch props :darkmode :change-theme-mode (not checked)))})
                (d/label {:for "checkbox"}
                         (d/div
                           {:class (str "cursor-pointer absolute top-0 left-0 right-0 bottom-0 rounded-full bg-gray-400 border-1 border-gray-400" (if checked " bg-gray-800"))}
                           " "
                           (d/div {:class (str "transition transform duration-500 shadow-md absolute rounded-full w-4 h-4 bg-gray-100 z-10" (if checked " ml-5"))
                                   :style {:top "50%"
                                           :left "0"
                                           :transform "translateY(-50%)"}}))))))

(defnc Header [props]
       (d/div {:class "flex justify-between"}
         ($ TitleRenderer {:darkmode? (:darkmode? props)})
         (d/div {:class "text-sm text-gray-700 flex"}
                (d/p {:class "mr-2"} "Dark Mode")
                ($ CheckBox {& props}))))

(defnc CardLogoWrapper [{:keys [icon]}]
       (d/div {:class "flex w-full justify-center"}
              (d/div
                {:style {:width "15px" :height "15px"}}
                (d/img {:src icon :width "100%" :height "100%"}))
              (d/p {:class "text-xs text-gray-700 ml-2"} "@nathanf")))

(defnc Followers [{:keys [darkmode? followers]}]
       (d/div {:class "flex flex-col"}
              (d/p {:class (str "text-5xl font-bold block flex justify-center" (if darkmode? " text-white " " text-black "))} followers)
              (d/p {:class "text-xs tracking-widest text-gray-500 uppercase block flex justify-center "} "Followers")))

(defnc TodayStats [{:keys [today today-icon today-color]}]
       (d/div {:class "flex flex-row justify-center"}
              (d/div {:class "h-full flex items-center mr-1"}
                (d/div
                  {:style {:width "10px" :height "10px"}}
                  (d/img {:src today-icon :width "100%" :height "100%"})))
              (d/p {:class (str "text-xs " (str "text-" today-color))} (str today " Today"))))

(defnc Card [data]
       (let [data# (:data data)]
         (d/div {:class "mt-8"}
                ($ CardWrapper {:darkmode? (:darkmode? data) :border-top-color (:border-top-color data#)}
                   ($ CardLogoWrapper {:icon (:icon data#)})
                   ($ Followers {:darkmode? (:darkmode? data) :followers (:followers data#)})
                   ($ TodayStats {:today (:today data#)
                                  :today-icon (:today-icon data#)
                                  :today-color (:today-color data#)})))))

(defclassified CardWrapOuter :div "flex justify-around flex-wrap")

(defclassified OverviewWrapper :div "flex flex-col mt-20")

(defclassified SmallCardWrapper :div "mt-4 flex flex-col justify-center items-center p-8 w-48 h-20 rounded-md"
               (fn [{:keys [darkmode?]}] (if darkmode? "bg-card-bg-dark" "bg-gray-200")))

(defnc SmallCard [{:keys [darkmode? data]}]
       (let [title (:title data)
             number (:number data)
             icon (:icon data)
             arrow (:arrow data)
             arrow-color (:arrow-color data)
             arrow-number (:arrow-number data)]
         ($ SmallCardWrapper {:darkmode? darkmode?}
            (d/div {:class "flex justify-between w-full"}
                   (d/div {:class (str "text-xs" (if darkmode? " text-gray-600 " " text-gray-800 "))} title)
                   (d/div
                     {:style {:width "15px" :height "15px"}}
                     (d/img {:src icon :width "100%" :height "100%"})))
            (d/div {:class "flex justify-between w-full mt-2"}
                   (d/div {:class (str "text-2xl font-bold" (if darkmode? " text-white " " text-black "))} number)
                   (d/div {:class "flex justify-center items-center text-xs"}
                          (d/div
                            {:style {:width "10px" :height "10px"}}
                            (d/img {:src arrow :width "100%" :height "100%"}))
                          (d/div {:class (str "pl-1 " (str "text-" arrow-color))} arrow-number))))))

(defnc HomeRenderer [props]
       (let [darkmode? (use-sub props :darkmode)]
         ($ HomepageWrapper {:style {:color "red"} :darkmode? darkmode?}
                   ($ InnerWrapper {:darkmode? darkmode?}
                      ($ Header {& props :darkmode? darkmode?})
                      ($ CardWrapOuter
                         (map #($ Card {:darkmode? darkmode? :data %}) data))
                      ($ OverviewWrapper
                         (d/div {:class "text-md text-gray-700 mb-6"} "Overview - Today")
                         (d/div {:class "grid md:grid-rows-2 gap-2 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 self-center"}
                                (map #($ SmallCard {:darkmode? darkmode? :data %}) small-card-data)))))))

(def Home (with-keechma HomeRenderer))