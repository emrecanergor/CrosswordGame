# Crossword Game - Bulmaca Oyunu
JAVA SWING - Crossword game

[![N|Solid](http://www.mediafire.com/convkey/f2ae/62n1qx4op2q96g2zg.jpg)](https://nodesource.com/products/nsolid)

### Oynanış
 - Yatay ve dikey olmak üzere 2 koordinata bölünmüş çözülmeyi bekleyen kelimeler bulunur.
 - Her bir harfe basıldığında bir yandaki kutucuğa geçiş yapar ve bir kutucuğa birden fazla karakter girilemez.
 - Sonuçları göster butonu ile çıkan emin misiniz ekranındaki cevaba göre random farklı bir oyuna geçiş yapar.

### İçerik
 - İçerisinde 10 farklı oyun barındıran bulmaca oyunu java'nın swing kütüphanesi kullanılarak yazılmıştır.
 - Her oyun için 1 class yaratılmış olup 10 oyun için 10 class mevcuttur. 
 - Basit bir veritabanı bağlantısıyla oyunid'sine random karar vererek tek class'a düşürülebilir bir yapıdadır.
 - 

### Yapılacaklar

 - Pek çok yeni bulmaca eklenebilir.
 - Veritabanı bağlantısı gerçekleştirip kendini tekrar eden class'lardan kaçınılarak sorular ve cevaplar veritabanından çekilebilir.
 - Giriş ekranı ile oyuna başlamadan ayarlar, müziği aç/kapa, başlat, çıkış buton ve işlevleri tasarlanabilir.

### Kullanım
 - SORU KISMINA '-' (tire) ve '(',')' (virgül) KARAKTERLERİ EKLENMEMELİ
 - YENİ EKLENECEK BULMACALAR İÇİN DİĞER CLASSLARI KOPYALA YAPIŞTIRLA ALIP SADECE CONSTRUCTOR VE CLASS ADI DEĞİŞTİRİLECEK, BULMACA VE CEVAPLARI STRING DİZİSİNE GİRİLECEK.
 - KAÇ TANE CLASS EKLENDİYSE ortak.class 'TA void main() 'DE, olustur() FONK., jtfdisableet() FONK., focuslost() FONK. (ANONYMOUS CLASS'TA) SWITCH CASE'E YENİ SORUNUN CLASS'I CASE EKLENECEK.
