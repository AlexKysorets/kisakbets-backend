package com.kysorets.kisakbets.service.purchasedetails;

import com.kysorets.kisakbets.model.PurchaseDetails;
import com.kysorets.kisakbets.model.Subscription;
import com.kysorets.kisakbets.repository.PurchaseDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseDetailsServiceImplementation implements PurchaseDetailsService{
    private final PurchaseDetailsRepository purchaseDetailsRepository;

    @Override
    public List<PurchaseDetails> getPurchaseDetailsByStatus(String status) {
        return purchaseDetailsRepository.getByStatus(status);
    }

    @Override
    public PurchaseDetails getPurchaseDetailsBySubscription(Subscription subscription) {
        return purchaseDetailsRepository.getBySubscription(subscription);
    }

    @Override
    public List<PurchaseDetails> getPurchaseDetails(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var purchaseDetails = purchaseDetailsRepository.findAll(pageable);
        return purchaseDetails.toList();
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
