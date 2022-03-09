package com.kysorets.kisakbets.service.purchasedetails;

import com.kysorets.kisakbets.model.PurchaseDetails;
import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.repository.PurchaseDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseDetailsServiceImplementation implements PurchaseDetailsService{
    private final PurchaseDetailsRepository purchaseDetailsRepository;

    @Override
    public PurchaseDetails getPurchaseDetailsByStatus(String status) {
        return purchaseDetailsRepository.getByStatus(status);
    }

    @Override
    public PurchaseDetails getPurchaseDetailsBySubscription(Subscription subscription) {
        return purchaseDetailsRepository.getBySubscription(subscription);
    }

    @Override
    public List<PurchaseDetails> getPurchaseDetails() {
        return purchaseDetailsRepository.findAll();
    }

    @Override
    public void deletePurchaseDetails(Subscription subscription) {
        purchaseDetailsRepository.deleteBySubscription(subscription);
    }

    @Override
    public void savePurchaseDetails(PurchaseDetails purchaseDetails) {
        purchaseDetailsRepository.save(purchaseDetails);
    }
}
