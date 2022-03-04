package com.kysorets.kisakbets.api;

import com.kysorets.kisakbets.model.PurchaseDetails;
import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.service.purchasedetails.PurchaseDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PurchaseDetailsController {
    private final PurchaseDetailsService purchaseDetailsService;

    // CREATE AND UPDATE
    @PostMapping("/purchase-detail")
    public void createOrUpdatePurchaseDetails(@RequestBody PurchaseDetails purchaseDetails) {
        purchaseDetailsService.savePurchaseDetails(purchaseDetails);
    }

    // READ BY STATUS
    @GetMapping("/purchase-detail/{status}")
    public PurchaseDetails getPurchaseDetailsByStatus(@PathVariable String status) {
        return purchaseDetailsService.getPurchaseDetailsByStatus(status);
    }

    // READ BY SUBSCRIPTION
    @GetMapping("/purchase-detail")
    public PurchaseDetails getPurchaseDetailsBySubscription(@RequestBody Subscription subscription) {
        return purchaseDetailsService.getPurchaseDetailsBySubscription(subscription);
    }

    // READ ALL
    @GetMapping("/purchase-details")
    public List<PurchaseDetails> getAllPurchaseDetails() {
        return purchaseDetailsService.getPurchaseDetails();
    }

    // DELETE
    @DeleteMapping("/purchase-detail}")
    public void deletePurchaseDetails(@RequestBody Subscription subscription) {
        purchaseDetailsService.deletePurchaseDetails(subscription);
    }
}
