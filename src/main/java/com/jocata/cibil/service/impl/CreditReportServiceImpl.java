package com.jocata.cibil.service.impl;

import com.jocata.cibil.dao.CreditReportDao;
import com.jocata.cibil.entity.*;
import com.jocata.cibil.form.*;
import com.jocata.cibil.service.CreditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditReportServiceImpl implements CreditReportService {

    @Autowired
    private CreditReportDao reportDao;

    @Override
    public CreditReportDTO createCreditReport(CreditReportDTO form) {
        CreditReport existing = reportDao.getCreditReportByPan(form.getCustomer().getPanNumber());
        if (existing == null) {
            CreditReport report = new CreditReport();
            report.setGeneratedOn(LocalDate.now());
            Customer customer = setCustomer(form.getCustomer(), report);
            report.setCustomer(customer);
            CibilScore cibilScore = setCibilScore(form.getCibilScore(), report);
            report.setCibilScore(cibilScore);
            List<Account> accountsList = setAccounts(form.getAccounts(), report);
            report.setAccounts(accountsList);
            List<Enquiry> enquiryList = setEnquiries(form.getEnquiries(), report);
            report.setEnquiries(enquiryList);
            List<Remark> remarkList = setRemarks(form.getRemarks(), report);
            report.setRemarks(remarkList);

            CreditReport returnedEntity = reportDao.createCreditReport(report);
            CreditReportDTO transformedEntity = entityToDTO(returnedEntity);
            return transformedEntity;
        } else {
            Customer existingCustomer = existing.getCustomer();

            if (existingCustomer != null && existingCustomer.getAddress() != null
                    && form.getCustomer() != null && form.getCustomer().getAddress() != null) {
                Address existingAddress = existingCustomer.getAddress();
                AddressDTO newAddress = form.getCustomer().getAddress();
                existingAddress.setLine(newAddress.getLine());
                existingAddress.setCity(newAddress.getCity());
                existingAddress.setStreet(newAddress.getStreet());
                existingAddress.setPincode(newAddress.getPincode());
            }

            if (form.getAccounts() != null) {
                List<Account> accounts = new ArrayList<>();
                for (AccountDTO accountDTO : form.getAccounts()) {
                    Account account = new Account();
                    account.setCreditReport(existing);
                    account.setAccountNumber(accountDTO.getAccountNumber());
                    account.setAccountType(accountDTO.getAccountType());
                    account.setMemberName(accountDTO.getMemberName());
                    account.setOwnership(accountDTO.getOwnership());
                    account.setDateOpened(accountDTO.getDateOpened());
                    account.setLastPaymentDate(accountDTO.getLastPaymentDate());
                    account.setCurrentBalance(accountDTO.getCurrentBalance());
                    account.setCreditLimit(accountDTO.getCreditLimit());
                    account.setSanctionedAmount(accountDTO.getSanctionedAmount());
                    account.setEmiAmount(accountDTO.getEmiAmount());
                    account.setTenureMonths(accountDTO.getTenureMonths());
                    account.setPaymentHistory(accountDTO.getPaymentHistory());
                    account.setAccountStatus(accountDTO.getAccountStatus());
                    accounts.add(account);
                }
                existing.setAccounts(accounts);
            }
            if (form.getEnquiries() != null) {

                List<Enquiry> enquiryList = new ArrayList<>();
                for (EnquiryDTO enquiryDTO : form.getEnquiries()) {
                    Enquiry enquiry = new Enquiry();
                    enquiry.setCreditReport(existing);
                    enquiry.setEnquiryDate(enquiryDTO.getEnquiryDate());
                    enquiry.setMemberName(enquiryDTO.getMemberName());
                    enquiry.setEnquiryPurpose(enquiryDTO.getEnquiryPurpose());
                    enquiry.setEnquiryAmount(enquiryDTO.getEnquiryAmount());

                    enquiryList.add(enquiry);
                }
                existing.setEnquiries(enquiryList);
            }

            if (!form.getRemarks().isEmpty()) {
                List<Remark> remarkList = new ArrayList<>();
                for (String str : form.getRemarks()) {
                    Remark remark = new Remark();
                    remark.setDescription(str);
                    remark.setCreditReport(existing);
                    remarkList.add(remark);
                }
                existing.setRemarks(remarkList);
            }
            CreditReport returnedEntity = reportDao.updateCreditReport(existing);
            CreditReportDTO transformedEntity = entityToDTO(returnedEntity);
            return transformedEntity;
        }
    }

    @Override
    public CreditReportDTO getCreditReportByPan(String panNumber) {

        CreditReport returnedEntity = reportDao.getCreditReportByPan(panNumber);
        CreditReportDTO transformedEntity = entityToDTO(returnedEntity);
        return transformedEntity;


    }

    private CreditReportDTO entityToDTO(CreditReport returnedEntity) {

        CreditReportDTO creditReportDTO = new CreditReportDTO();
        creditReportDTO.setReportId(returnedEntity.getReportId());
        creditReportDTO.setGeneratedOn(returnedEntity.getGeneratedOn());

        CustomerDTO customerDTO = setCustomerDTO(returnedEntity);
        creditReportDTO.setCustomer(customerDTO);

        CibilScoreDTO cibilScoreDTO = setCibilScoreDTO(returnedEntity);
        creditReportDTO.setCibilScore(cibilScoreDTO);

        List<AccountDTO> accountDTOList = setAccountDTOs(returnedEntity);
        creditReportDTO.setAccounts(accountDTOList);

        List<EnquiryDTO> enquiryDTOList = setEntityDTOs(returnedEntity);
        creditReportDTO.setEnquiries(enquiryDTOList);

        List<String> remarks = setRemarksDTO(returnedEntity);
        creditReportDTO.setRemarks(remarks);

        return creditReportDTO;
    }

    private List<String> setRemarksDTO(CreditReport returnedEntity) {
        List<String> remarks = new ArrayList<>();
        for (Remark remark : returnedEntity.getRemarks()) {
            remarks.add(remark.getDescription());
        }
        return remarks;
    }

    private List<EnquiryDTO> setEntityDTOs(CreditReport returnedEntity) {
        List<EnquiryDTO> enquiryDTOList = new ArrayList<>();
        for (Enquiry enquiry : returnedEntity.getEnquiries()) {
            EnquiryDTO enquiryDTO = new EnquiryDTO();
            enquiryDTO.setEnquiryDate(enquiry.getEnquiryDate());
            enquiryDTO.setMemberName(enquiry.getMemberName());
            enquiryDTO.setEnquiryPurpose(enquiry.getEnquiryPurpose());
            enquiryDTO.setEnquiryAmount(enquiry.getEnquiryAmount());
            enquiryDTOList.add(enquiryDTO);
        }
        return enquiryDTOList;
    }

    private List<AccountDTO> setAccountDTOs(CreditReport returnedEntity) {
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (Account account : returnedEntity.getAccounts()) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountNumber(account.getAccountNumber());
            accountDTO.setAccountType(account.getAccountType());
            accountDTO.setMemberName(account.getMemberName());
            accountDTO.setOwnership(account.getOwnership());
            accountDTO.setDateOpened(account.getDateOpened());
            accountDTO.setLastPaymentDate(account.getLastPaymentDate());
            accountDTO.setCurrentBalance(account.getCurrentBalance());
            accountDTO.setCreditLimit(account.getCreditLimit());
            accountDTO.setSanctionedAmount(account.getSanctionedAmount());
            accountDTO.setEmiAmount(account.getEmiAmount());
            accountDTO.setTenureMonths(account.getTenureMonths());
            accountDTO.setPaymentHistory(account.getPaymentHistory());
            accountDTO.setAccountStatus(account.getAccountStatus());

            accountDTOList.add(accountDTO);
        }
        return accountDTOList;
    }

    private CibilScoreDTO setCibilScoreDTO(CreditReport returnedEntity) {
        CibilScoreDTO cibilScoreDTO = new CibilScoreDTO();
        cibilScoreDTO.setScore(returnedEntity.getCibilScore().getScore());
        cibilScoreDTO.setScoreDate(returnedEntity.getCibilScore().getScoreDate());
        cibilScoreDTO.setRiskGrade(returnedEntity.getCibilScore().getRiskGrade());
        return cibilScoreDTO;
    }

    private CustomerDTO setCustomerDTO(CreditReport returnedEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFullName(returnedEntity.getCustomer().getFullName());
        customerDTO.setDateOfBirth(returnedEntity.getCustomer().getDateOfBirth());
        customerDTO.setGender(returnedEntity.getCustomer().getGender());
        customerDTO.setPanNumber(returnedEntity.getCustomer().getPanNumber());
        customerDTO.setMobile(returnedEntity.getCustomer().getMobile());
        customerDTO.setEmail(returnedEntity.getCustomer().getEmail());
        customerDTO.setAadhaar(returnedEntity.getCustomer().getAadhaar());
        AddressDTO addressDTO = setAddressDTO(returnedEntity);
        customerDTO.setAddress(addressDTO);
        return customerDTO;
    }

    private AddressDTO setAddressDTO(CreditReport returnedEntity) {
        AddressDTO addressDTO = new AddressDTO();
        if (returnedEntity.getCustomer() != null && returnedEntity.getCustomer().getAddress() != null) {
            addressDTO.setLine(returnedEntity.getCustomer().getAddress().getLine());
            addressDTO.setCity(returnedEntity.getCustomer().getAddress().getCity());
            addressDTO.setStreet(returnedEntity.getCustomer().getAddress().getStreet());
            addressDTO.setPincode(returnedEntity.getCustomer().getAddress().getPincode());
        }
        return addressDTO;
    }

    private List<Remark> setRemarks(List<String> remarks, CreditReport report) {
        List<Remark> remarkList = new ArrayList<>();
        for (String str : remarks) {
            Remark remark = new Remark();
            remark.setCreditReport(report);
            remark.setDescription(str);
            remarkList.add(remark);
        }
        return remarkList;
    }

    private List<Enquiry> setEnquiries(List<EnquiryDTO> enquiries, CreditReport report) {
        List<Enquiry> enquiryList = new ArrayList<>();
        for (EnquiryDTO enquiryDTO : enquiries) {
            Enquiry enquiry = new Enquiry();
            enquiry.setCreditReport(report);
            enquiry.setEnquiryDate(enquiryDTO.getEnquiryDate());
            enquiry.setMemberName(enquiryDTO.getMemberName());
            enquiry.setEnquiryPurpose(enquiryDTO.getEnquiryPurpose());
            enquiry.setEnquiryAmount(enquiryDTO.getEnquiryAmount());

            enquiryList.add(enquiry);
        }
        return enquiryList;
    }

    private List<Account> setAccounts(List<AccountDTO> accounts, CreditReport report) {
        List<Account> accountList = new ArrayList<>();
        for (AccountDTO acc : accounts) {
            Account account = new Account();
            account.setCreditReport(report);
            account.setAccountNumber(acc.getAccountNumber());
            account.setAccountType(acc.getAccountType());
            account.setMemberName(acc.getMemberName());
            account.setOwnership(acc.getOwnership());
            account.setDateOpened(acc.getDateOpened());
            account.setLastPaymentDate(acc.getLastPaymentDate());
            account.setCurrentBalance(acc.getCurrentBalance());
            account.setCreditLimit(acc.getCreditLimit());
            account.setSanctionedAmount(acc.getSanctionedAmount());
            account.setEmiAmount(acc.getEmiAmount());
            account.setTenureMonths(acc.getTenureMonths());
            account.setPaymentHistory(acc.getPaymentHistory());
            account.setAccountStatus(acc.getAccountStatus());

            accountList.add(account);
        }
        return accountList;
    }

    private CibilScore setCibilScore(CibilScoreDTO cibilScoreDTO, CreditReport report) {
        CibilScore cibilScore = new CibilScore();
        cibilScore.setCreditReport(report);
        cibilScore.setScore(cibilScoreDTO.getScore());
        cibilScore.setScoreDate(cibilScoreDTO.getScoreDate());
        cibilScore.setRiskGrade(cibilScoreDTO.getRiskGrade());
        return cibilScore;
    }

    private Customer setCustomer(CustomerDTO customerDTO, CreditReport report) {
        Customer customer = new Customer();
        customer.setCreditReport(report);
        customer.setFullName(customerDTO.getFullName());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());
        customer.setGender(customerDTO.getGender());
        customer.setPanNumber(customerDTO.getPanNumber());
        customer.setMobile(customerDTO.getMobile());
        customer.setEmail(customerDTO.getEmail());
        customer.setAadhaar(customerDTO.getAadhaar());
        if (customerDTO.getAddress() != null) {
            Address address = new Address();
            address.setCustomer(customer);
            address.setLine(customerDTO.getAddress().getLine());
            address.setCity(customerDTO.getAddress().getCity());
            address.setStreet(customerDTO.getAddress().getStreet());
            address.setPincode(customerDTO.getAddress().getPincode());
            customer.setAddress(address);
        }
        return customer;
    }
}
