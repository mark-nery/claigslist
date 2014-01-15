(ns claigslist.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [hickory.select :as s]
            [clojure.string :as string]
            [hickory.core :refer :all]))

(defn listing-title
  [row]
  (get
   (get-in
    (get (-> (s/select (s/child (s/class "pl") (s/tag :a)) row)) 0)
    [:content])
   0))

(defn listing-link [row]
  (get-in (get (get-in row [:content]) 1) [:attrs :href]))

(defn listing-id [row]
  (get-in row [:attrs :data-pid]))

(defn listing-data [row]
  {:id (listing-id row)
   :title (listing-title row)
   :link (listing-link row)})

(defn for-sale
  [city q]
  (str "http://" city ".craigslist.org/search/sss?" 
       (client/generate-query-string q)))

(defn site-htree
  [uri]
   (-> (client/get uri) :body parse as-hickory))

(defn search
  [city query]
  (map listing-data (-> (s/select (s/class "row") (site-htree (for-sale city query))))))

