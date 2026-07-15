# Flick行列輸入法
**Flick行列輸入法** 是一個 100% 使用 **Kotlin** 與 **Jetpack Compose** 開發的現代化Android輸入法。

本專案的核心主打為 **Flick行列** 輸入模式，旨在透過flick的方式拖曳按鍵，在手機上提供更好按的 **行列30** 輸入體驗。

靈感源自於 **Flick日語輸入法** 以及 **行列10** 輸入法。行列10輸入法雖然按鍵少，但是每個字拆解出來的字根碼數量，很多都比行列30多了很多，而行列30的字根碼帶有上(^)、中(-)、下(v)的元素，正好也能對應成flick拖曳的方向，因而設計出了這一款輸入法。

## 核心特色
* **Flick行列模式**：創新的 **行列30** 輸入方式，為本專案的核心主軸，將行列30傳統字根中的上、中、下元素，直覺地映射至觸控螢幕的flick拖曳操作。專為手機窄螢幕配置設計。
* **特殊功能鍵與D-Pad**：
  * **D-Pad**：在數個輸入模式中皆可單獨使用，提供拖曳實現游標移動的功能。
  * **特殊功能鍵**：此功能僅在 **Flick行列** 模式中實作。使用者可以透過向下拖曳不放，或向上拖曳不放，來分別觸發 `Ctrl` 或 `Shift` 狀態。而D-Pad鍵會與特殊功能鍵產生交互作用，進而實現全選、複製、貼上、復原、重做，以及選取上下行或前後字元等進階文字編輯功能，免去觸控螢幕長按選取文字的繁瑣操作。
* **Flick語言切換鍵**：語言切換鍵使用flick的方式，能夠一步直達指定的輸入模式，免去傳統設計需要連續點擊依序切換語言或需要設計多個不同的按鍵。

## 輸入模式
* **Flick行列** ：字根碼與行列30相同，可以 **點擊數字鍵輸入「-」**、**向上拖曳數字鍵輸入「^」**、**向下拖曳數字鍵輸入「v」**，來輸入行列30的字根碼。
* **行列30**：標準的行列30輸入方式。
* **英文**：一般的英文鍵盤，雙擊⇧可以鎖定大寫。
* **數字**：提供0~9以及多種符號可供輸入。
* **符號**：提供多種常用符號、箭頭符號、數學符號以及希臘字母。
* **Emoji**：提供各式各樣的Emoji，並且可以透過flick的方式選擇膚色。

## 架構設計
* 本專案不使用傳統的Android XML佈局，而是全面使用 **Jetpack Compose** 開發，並採用高度模組化的架構設計。
* **模組化分工**：不同的輸入模式皆拆分為獨立的資料夾模組。核心程式碼只負責通用的排版與手勢分發等，新增或修改單一輸入模式不需更動到核心邏輯，易於後續擴充與維護。
* **集中管理的尺寸與主題**：統一管理所有的尺寸與色彩，讓鍵盤能依據設備狀態（如手機或平板、直向或橫向、深色或淺色）自動適應並重繪。
* **資料與 UI 解耦**：鍵盤的UI排版、觸控手勢分發與底層的字根解碼、邏輯處理完全隔離。UI層僅透過抽象的資料模型進行渲染，並將手勢意圖轉換為通用的按鍵事件交由後端處理。

## 授權條款 (License)
本專案自版本`v1.0.0`及其所有歷史 Commits、原始碼，自始全面採用 MIT 授權條款釋出。\
不論您取得原始碼時其中是否包含 LICENSE 檔案，凡使用、修改、重製或散佈本專案之程式碼，皆須強制遵守 MIT 條款，於衍生作品中保留原作者之版權聲明（`Copyright (c) 2026 crest247`）與授權文字。

This project, starting from version v1.0.0 and including all historical commits and source code, is licensed under the MIT License from its inception.\
Regardless of whether a LICENSE file is included in the source code you obtained, any use, modification, reproduction, or distribution of this code must strictly adhere to the MIT License, which requires retaining the original copyright notice (`Copyright (c) 2026 crest247`) and the license text in any derivative works.
