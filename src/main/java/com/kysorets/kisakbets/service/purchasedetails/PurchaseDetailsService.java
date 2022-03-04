package com.kysorets.kisakbets.service.purchasedetails;

import com.kysorets.kisakbets.model.PurchaseDetails;
import com.kysorets.kisakbets.model.Subscription;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseDetailsService {
    PurchaseDetails getPurchaseDetailsByStatus(String status);
    PurchaseDetails getPurchaseDetailsBySubscription(Subscription subscription);
    List<PurchaseDetails> getPurchaseDetails();
    void deletePurchaseDetails(Subscription subscription);
    void savePurchaseDetails(PurchaseDetails purchaseDetails);
}
