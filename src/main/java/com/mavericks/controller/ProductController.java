package com.mavericks.controller;

import com.mavericks.model.Product;
import com.mavericks.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public CommandLineRunner seedProducts() {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(

                    // ── HOODIES ──────────────────────────────────────────────────
                    Product.builder().id("p1")
                        .name("Team Hoodie — Double Zipper")
                        .category("Hoodies").collectionName("Drop 01")
                        .description("400GSM heavyweight fleece. Oversized fit. Unisex sizing. Available in your team's colorway.")
                        .quote("Your girlfriend asked for my number. I gave her my lap time.")
                        .price(89).stock(50)
                        .colors("Red Bull Navy,Ferrari Red,McLaren Papaya,Mercedes Teal,Onyx Black")
                        .sizes("XS,S,M,L,XL,XXL")
                        .images("https://images.pexels.com/photos/5840463/pexels-photo-5840463.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop,https://images.pexels.com/photos/5319493/pexels-photo-5319493.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop,https://images.pexels.com/photos/5319577/pexels-photo-5319577.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(true).newDrop(true)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("hoodie,f1,drop01,heavyweight,unisex").build(),

                    Product.builder().id("p8")
                        .name("Podium Pullover Hoodie")
                        .category("Hoodies").collectionName("Core Essentials")
                        .description("350GSM fleece. Classic pullover style. Large team number print on back. Kangaroo pocket. The hoodie for watching qualifying at 2am.")
                        .quote("She stole my hoodie. I let her. It's called a pit stop strategy.")
                        .price(69).stock(60)
                        .colors("Onyx Black,Carbon Grey,Ferrari Red,Mercedes Teal,McLaren Papaya")
                        .sizes("XS,S,M,L,XL,XXL")
                        .images("https://images.pexels.com/photos/5840463/pexels-photo-5840463.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("hoodie,f1,pullover,classic").build(),

                    // ── DRIVER HOODIES ────────────────────────────────────────────
                    Product.builder().id("p17")
                        .name("Leclerc 16 Fan Hoodie")
                        .category("Hoodies").collectionName("Driver Collection")
                        .description("350GSM fleece. Charles Leclerc #16 Ferrari team colorway. Oversized fit. The hoodie that goes faster than your excuses.")
                        .quote("She said she likes fast guys. Charles said hold my steering wheel.")
                        .price(79).stock(40)
                        .colors("Ferrari Red,Onyx Black")
                        .sizes("XS,S,M,L,XL,XXL")
                        .images("https://images.pexels.com/photos/5319493/pexels-photo-5319493.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(true).newDrop(true)
                        .team("Ferrari").driver("Charles Leclerc").season(2026).edition("fan").limited(false).discount(0)
                        .tags("hoodie,f1,ferrari,leclerc,driver,fan").build(),

                    // ── T-SHIRTS ─────────────────────────────────────────────────
                    Product.builder().id("p2")
                        .name("Race Day Tee — Statement Edition")
                        .category("T-Shirts").collectionName("Drop 01")
                        .description("220GSM premium cotton. F1 circuit prints, driver numbers, team graphics. Made for race day and every day after.")
                        .quote("She said she likes fast guys. I handed her my race tee.")
                        .price(39).stock(120)
                        .colors("Onyx Black,Race White,Ferrari Red,McLaren Papaya,Carbon Grey")
                        .sizes("XS,S,M,L,XL,XXL")
                        .images("https://images.pexels.com/photos/5493535/pexels-photo-5493535.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop,https://images.pexels.com/photos/5319298/pexels-photo-5319298.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(true).newDrop(true)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("tshirt,f1,drop01,cotton,circuit").build(),

                    Product.builder().id("p16")
                        .name("Max 33 Race Tee")
                        .category("T-Shirts").collectionName("Driver Collection")
                        .description("220GSM cotton. Max Verstappen #33 Red Bull colorway. Bold number graphic on front. For the ones who go flat out even in traffic.")
                        .quote("She asked if I'm always this fast. I said only when it matters.")
                        .price(45).stock(35)
                        .colors("Red Bull Navy,Race White")
                        .sizes("XS,S,M,L,XL,XXL")
                        .images("https://images.pexels.com/photos/5493535/pexels-photo-5493535.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(true).newDrop(true)
                        .team("Red Bull Racing").driver("Max Verstappen").season(2026).edition("limited").limited(true).discount(0)
                        .tags("tshirt,f1,redbull,verstappen,limited,driver").build(),

                    Product.builder().id("p19")
                        .name("Hamilton 44 Legacy Tee")
                        .category("T-Shirts").collectionName("Driver Collection")
                        .description("220GSM cotton. Lewis Hamilton #44 Mercedes colorway. Legacy edition — celebrating the greatest of all time. Limited run.")
                        .quote("She said no one's irreplaceable. I said check the record books.")
                        .price(49).stock(30)
                        .colors("Mercedes Teal,Onyx Black")
                        .sizes("XS,S,M,L,XL,XXL")
                        .images("https://images.pexels.com/photos/5319298/pexels-photo-5319298.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(true)
                        .team("Mercedes-AMG Petronas").driver("Lewis Hamilton").season(2026).edition("limited").limited(true).discount(10)
                        .tags("tshirt,f1,mercedes,hamilton,limited,driver,legacy").build(),

                    // ── JACKETS ──────────────────────────────────────────────────
                    Product.builder().id("p3")
                        .name("Pit Lane Leather Jacket")
                        .category("Jackets").collectionName("Drop 01")
                        .description("Premium faux leather. Slim racing silhouette. Team badge embroidery on chest and back. The jacket that turns every street into a pit lane.")
                        .quote("Your girlfriend loves my jacket. Can't blame her — so does the paddock.")
                        .price(149).stock(30)
                        .colors("Onyx Black,Ferrari Red,Race White")
                        .sizes("S,M,L,XL")
                        .images("https://images.pexels.com/photos/5236996/pexels-photo-5236996.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(true).newDrop(true)
                        .team("Ferrari").driver("").season(2026).edition("premium").limited(true).discount(0)
                        .tags("jacket,f1,ferrari,leather,premium,limited").build(),

                    Product.builder().id("p5")
                        .name("Starting Grid Bomber")
                        .category("Jackets").collectionName("Drop 01")
                        .description("Satin-finish bomber. Racing stripe on sleeves. Team colorway lining inside. Drop the jacket, win the room.")
                        .quote("Your girlfriend called. She wants the bomber back. I said no.")
                        .price(119).stock(25)
                        .colors("Onyx Black,Red Bull Navy,Ferrari Red,McLaren Papaya")
                        .sizes("S,M,L,XL,XXL")
                        .images("https://images.pexels.com/photos/5319577/pexels-photo-5319577.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(true)
                        .team("Red Bull Racing").driver("").season(2026).edition("limited").limited(true).discount(0)
                        .tags("jacket,f1,redbull,bomber,limited,satin").build(),

                    Product.builder().id("p18")
                        .name("Norris 4 McLaren Bomber")
                        .category("Jackets").collectionName("Driver Collection")
                        .description("Satin-finish bomber. Lando Norris #4 McLaren Papaya colorway. Racing stripe detail. The jacket that smiles back at you.")
                        .quote("She said I'm too competitive. I said then stop losing to me.")
                        .price(129).stock(20)
                        .colors("McLaren Papaya,Onyx Black")
                        .sizes("S,M,L,XL")
                        .images("https://images.pexels.com/photos/5236996/pexels-photo-5236996.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(true)
                        .team("McLaren").driver("Lando Norris").season(2026).edition("premium").limited(true).discount(0)
                        .tags("jacket,f1,mclaren,norris,premium,driver,bomber").build(),

                    // ── CAPS ─────────────────────────────────────────────────────
                    Product.builder().id("p4")
                        .name("Paddock Club Cap — Structured")
                        .category("Caps").collectionName("Drop 01")
                        .description("Structured 6-panel cap. Embroidered F1 team logos. Adjustable strap. The paddock look without the VIP pass.")
                        .quote("She peaked when she saw the cap. I peaked at Turn 1.")
                        .price(29).stock(100)
                        .colors("Red Bull Navy,Ferrari Red,McLaren Papaya,Mercedes Teal,Onyx Black,Race White")
                        .sizes("One Size")
                        .images("https://images.pexels.com/photos/5899133/pexels-photo-5899133.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(true)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("cap,f1,paddock,structured,embroidered").build(),

                    Product.builder().id("p7")
                        .name("DRS Cap — Flat Brim")
                        .category("Caps").collectionName("Drop 01")
                        .description("Flat brim, structured crown. F1 circuit map embroidered on front. Snapback closure. Street-ready, race-approved.")
                        .quote("She slid into my DMs. I was already in DRS.")
                        .price(32).stock(90)
                        .colors("Onyx Black,Carbon Grey,Ferrari Red,Mercedes Teal")
                        .sizes("One Size")
                        .images("https://images.pexels.com/photos/5899133/pexels-photo-5899133.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(true)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("cap,f1,flatbrim,drs,snapback").build(),

                    Product.builder().id("p20")
                        .name("Alonso 14 Cap")
                        .category("Caps").collectionName("Driver Collection")
                        .description("Structured cap. Fernando Alonso #14 Aston Martin green colorway. Two-time champion energy, every single day.")
                        .quote("She said age is just a number. Fernando said so is 14.")
                        .price(34).stock(70)
                        .colors("Aston Martin Green,Onyx Black")
                        .sizes("One Size")
                        .images("https://images.pexels.com/photos/5899133/pexels-photo-5899133.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(true)
                        .team("Aston Martin").driver("Fernando Alonso").season(2026).edition("fan").limited(false).discount(0)
                        .tags("cap,f1,astonmartin,alonso,driver,fan").build(),

                    // ── POLO SHIRTS ──────────────────────────────────────────────
                    Product.builder().id("p6")
                        .name("Podium Polo")
                        .category("Polo Shirts").collectionName("Core Essentials")
                        .description("Pique cotton polo. Team logo embroidered on chest. Clean, sharp, paddock-ready. The polo you wear when you mean business.")
                        .quote("She said dress to impress. I showed up in the podium polo. Race over.")
                        .price(49).stock(80)
                        .colors("Ferrari Red,Mercedes Teal,McLaren Papaya,Onyx Black,Race White")
                        .sizes("XS,S,M,L,XL")
                        .images("https://images.pexels.com/photos/2008949/pexels-photo-2008949.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("polo,f1,classic,paddock,smart").build(),

                    // ── ACCESSORIES ──────────────────────────────────────────────
                    Product.builder().id("p9")
                        .name("Pit Wall Scarf")
                        .category("Accessories").collectionName("Core Essentials")
                        .description("Soft knit scarf. Team colors woven in stripes. For race nights when it gets cold at the screen.")
                        .quote("She wrapped herself in my scarf. Told her it comes with the car.")
                        .price(19).stock(150)
                        .colors("Red Bull Navy,Ferrari Red,Mercedes Teal,McLaren Papaya")
                        .sizes("One Size")
                        .images("https://images.pexels.com/photos/5319298/pexels-photo-5319298.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("accessory,f1,scarf,winter,knit").build(),

                    Product.builder().id("p10")
                        .name("Paddock Tote Bag")
                        .category("Accessories").collectionName("Core Essentials")
                        .description("Heavy canvas tote. F1 circuit print or team logo. Double stitched handles. Carry your race day essentials in style.")
                        .quote("She grabbed my bag. I told her the car's in the garage.")
                        .price(15).stock(200)
                        .colors("Onyx Black,Race White,Carbon Grey")
                        .sizes("One Size")
                        .images("https://images.pexels.com/photos/5493535/pexels-photo-5493535.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("accessory,f1,tote,bag,canvas").build(),

                    Product.builder().id("p11")
                        .name("Race Grid Phone Case")
                        .category("Accessories").collectionName("Core Essentials")
                        .description("Hard shell, matte finish. F1 circuit maps or team livery designs. Protect your phone like your car protects its driver.")
                        .quote("She texted me at 2am. I was watching qualifying. Priorities.")
                        .price(12).stock(300)
                        .colors("Ferrari Livery,Red Bull Livery,McLaren Livery,Mercedes Livery")
                        .sizes("iPhone 13,iPhone 14,iPhone 15,Samsung S23,Samsung S24")
                        .images("https://images.pexels.com/photos/5319491/pexels-photo-5319491.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("accessory,f1,phonecase,livery,tech").build(),

                    Product.builder().id("p12")
                        .name("Pit Stop Keychain")
                        .category("Accessories").collectionName("Core Essentials")
                        .description("Die-cast metal. Team logo or F1 car silhouette. Polished chrome finish. Small but loud. Just like a good lap time.")
                        .quote("She asked what the keychain is for. I said the car she can't afford.")
                        .price(8).stock(500)
                        .colors("Chrome,Ferrari Red,Red Bull Navy,McLaren Papaya,Mercedes Teal")
                        .sizes("One Size")
                        .images("https://images.pexels.com/photos/5319298/pexels-photo-5319298.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("accessory,f1,keychain,metal,chrome").build(),

                    Product.builder().id("p13")
                        .name("Race Day Wristband")
                        .category("Accessories").collectionName("Core Essentials")
                        .description("Silicone wristband. Team color. Driver number or slogan printed. Stack them. Wear them. Represent your team.")
                        .quote("She noticed the wristband first. The car second. Both hit different.")
                        .price(5).stock(1000)
                        .colors("Red Bull Navy,Ferrari Red,McLaren Papaya,Mercedes Teal,Williams Blue,Alpine Pink")
                        .sizes("Standard,Large")
                        .images("https://images.pexels.com/photos/5899133/pexels-photo-5899133.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("accessory,f1,wristband,silicone,team").build(),

                    Product.builder().id("p15")
                        .name("Pit Lane Socks")
                        .category("Accessories").collectionName("Core Essentials")
                        .description("Combed cotton. Team color stripes. Reinforced heel and toe. Mid-calf length. The most underrated part of the fit.")
                        .quote("She said she judges men by their socks. Challenge accepted.")
                        .price(9).stock(400)
                        .colors("Red Bull Navy,Ferrari Red,McLaren Papaya,Mercedes Teal,Onyx Black")
                        .sizes("S/M,L/XL")
                        .images("https://images.pexels.com/photos/5319577/pexels-photo-5319577.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("accessory,f1,socks,cotton,team").build(),

                    // ── COLLECTIBLES ─────────────────────────────────────────────
                    Product.builder().id("p14")
                        .name("Wall of Champions Poster")
                        .category("Collectibles").collectionName("Core Essentials")
                        .description("High-quality matte print. F1 circuit maps, legendary race moments, driver portraits. Frame included. Turn your wall into a paddock.")
                        .quote("She came for the poster. Stayed for the driver. Classic.")
                        .price(22).stock(200)
                        .colors("Classic Print,Circuit Map,Driver Portrait")
                        .sizes("A3,A2")
                        .images("https://images.pexels.com/photos/5840463/pexels-photo-5840463.jpeg?auto=compress&cs=tinysrgb&w=900&h=1100&fit=crop")
                        .featured(false).newDrop(false)
                        .team("").driver("").season(2026).edition("fan").limited(false).discount(0)
                        .tags("collectible,f1,poster,wall,art,print").build()

                ));
                System.out.println("✓ 20 F1 products seeded");
            }
        };
    }

    // GET /api/products
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<Map<String, Object>> payload = products.stream().map(this::toMap).collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("success", true, "data", payload));
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable String id) {
        return productRepository.findById(id)
            .map(p -> ResponseEntity.ok(Map.of("success", true, "data", toMap(p))))
            .orElse(ResponseEntity.status(404).body(Map.of("success", false, "message", "Product not found")));
    }

    // GET /api/products/search?team=Ferrari&driver=Leclerc&category=Hoodies&limited=true
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String driver,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean limited) {

        List<Map<String, Object>> result = productRepository.findAll().stream()
            .filter(p -> team     == null || team.equalsIgnoreCase(p.getTeam()))
            .filter(p -> driver   == null || driver.equalsIgnoreCase(p.getDriver()))
            .filter(p -> category == null || category.equalsIgnoreCase(p.getCategory()))
            .filter(p -> limited  == null || p.isLimited() == limited)
            .map(this::toMap)
            .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of("success", true, "data", result));
    }

    private Map<String, Object> toMap(Product p) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id",          p.getId());
        m.put("name",        p.getName());
        m.put("category",    p.getCategory());
        m.put("collection",  p.getCollectionName());
        m.put("description", p.getDescription());
        m.put("quote",       p.getQuote());
        m.put("price",       p.getPrice());
        m.put("stock",       p.getStock());
        m.put("colors",      csv(p.getColors()));
        m.put("sizes",       csv(p.getSizes()));
        m.put("images",      csv(p.getImages()));
        m.put("featured",    p.isFeatured());
        m.put("new_drop",    p.isNewDrop());
        // F1-specific
        m.put("team",        p.getTeam());
        m.put("driver",      p.getDriver());
        m.put("season",      p.getSeason());
        m.put("edition",     p.getEdition());
        m.put("limited",     p.isLimited());
        m.put("discount",    p.getDiscount());
        m.put("tags",        csv(p.getTags()));
        return m;
    }

    private List<String> csv(String v) {
        if (v == null || v.isBlank()) return List.of();
        return Arrays.stream(v.split(",")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }
}
