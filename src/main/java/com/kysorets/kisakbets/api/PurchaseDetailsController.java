package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.PurchaseDetails;
import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.service.purchasedetails.PurchaseDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PurchaseDetailsController {
    private final PurchaseDetailsService purchaseDetailsService;

    // CREATE
    @PostMapping("/purchase-detail")
    public void createPurchaseDetails(@RequestBody PurchaseDetails purchaseDetails) {
        purchaseDetailsService.savePurchaseDetails(purchaseDetails);
    }

    // UPDATE
    @PutMapping("/purchase-detail")
    public void updatePurchaseDetail(@RequestBody PurchaseDetails purchaseDetails) {
        purchaseDetailsService.savePurchaseDetails(purchaseDetails);
    }

    // READ BY STATUS
    @GetMapping("/purchase-detail/{status}")
    public ResponseEntity<List<PurchaseDetails>> getPurchaseDetailsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(purchaseDetailsService.getPurchaseDetailsByStatus(status));
    }

    // READ BY SUBSCRIPTION
    @GetMapping("/purchase-detail")
    public ResponseEntity<PurchaseDetails> getPurchaseDetailsBySubscription(@RequestBody Subscription subscription) {
        return ResponseEntity.ok(purchaseDetailsService.getPurchaseDetailsBySubscription(subscription));
    }

    // READ ALL
    @GetMapping("/purchase-details")
    public ResponseEntity<List<PurchaseDetails>> getAllPurchaseDetails(@RequestParam(required = false, defaultValue = "0") int page,
                                                                       @RequestParam(required = false, defaultValue = "10") int size) {
        return ResponseEntity.ok(purchaseDetailsService.getPurchaseDetails(page, size));
    }

    // DELETE
    @DeleteMapping("/purchase-detail")
    public void deletePurchaseDetails(@RequestBody Subscription subscription) {
        purchaseDetailsService.deletePurchaseDetails(subscription);
    }
}
