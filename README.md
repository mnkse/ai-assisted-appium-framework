# AI-Assisted Appium Framework

Android uygulamaları için Java, Maven ve Appium ile hazırlanmış mobil test otomasyonu çalışması. Örnek uygulama **Sauce Labs My Demo App** üzerinde ürün kataloğundan sepete uzanan kullanıcı akışını doğrular.

## Kullanılan araçlar

- Java 21 ve Maven
- Appium 3 ve UiAutomator2 sürücüsü
- Android SDK, ADB ve Android emülatörü
- JUnit tabanlı Maven test çalıştırma ve Surefire raporları
- Appium MCP ile ekran inceleme ve test senaryosu keşfi

## Test akışı

1. Uygulamadaki ürün kataloğunu aç.
2. Bir ürün seç ve **Add to cart** ile sepete ekle.
3. **View cart** ekranını aç.
4. Ürün adını, miktarını ve fiyatını kontrol et.

Bu akış emülatörde çalıştırıldı; Maven çıktısında `Tests run: 1, Failures: 0, Errors: 0, Skipped: 0` ve `BUILD SUCCESS` görüldü.

## Yerel kurulum

1. Java 21, Maven, Node.js, Android SDK ve Android emülatörünü kur.
2. Appium sunucusunu ve Android sürücüsünü hazırla:

   ```bash
   npm install -g appium
   appium driver install uiautomator2
   ```

3. Emülatörü başlat ve `adb devices` ile erişilebilir olduğunu doğrula.
4. Test edilen uygulamanın emülatörde kurulu olduğundan emin ol. Kullanılan paket: `com.saucelabs.mydemoapp.android.test`.
5. Ayrı bir terminalde Appium sunucusunu başlat:

   ```bash
   appium --address 127.0.0.1 --port 4723
   ```

6. Projenin kök dizininde testi çalıştır:

   ```bash
   mvn test
   ```

Test sırasında emülatör penceresini açık tutarak adımları izleyebilirsin. Maven test raporları `target/surefire-reports/` altında oluşur. Proje ekran görüntüsü üretiyorsa bunlar ilgili `screenshots/` klasöründe bulunur.

## AI destekli çalışma

Appium MCP ile uygulamanın ekran yapısı ve elemanları incelendi; ürün seçimi ve sepet akışı keşfedilerek otomasyon senaryosu oluşturuldu. MCP, testlerin yerine geçmez: doğrulamalar Maven üzerinden çalışan Appium testiyle yapılır.

## Notlar

- Çalıştırmadan önce testin kullandığı cihaz adı, uygulama yolu/paketi ve Appium adresinin kendi ortamına uyduğunu kontrol et.
- `noReset=true` kullanıldığında sepet önceki çalışmadan dolu kalabilir. Testi tekrarlarken uygulamanın başlangıç durumunu kontrol et.
- `target/`, yerel IDE ayarları ve gizli bilgiler Git deposuna eklenmemelidir.
