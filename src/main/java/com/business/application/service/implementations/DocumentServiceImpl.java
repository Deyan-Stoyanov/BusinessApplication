package com.business.application.service.implementations;

import com.business.application.entity.Document;
import com.business.application.entity.Shift;
import com.business.application.entity.binding.DocumentBindingModel;
import com.business.application.entity.view.DocumentViewModel;
import com.business.application.entity.view.ShiftViewModel;
import com.business.application.enumerations.ShiftType;
import com.business.application.repository.DocumentRepository;
import com.business.application.repository.ShiftRepository;
import com.business.application.service.DocumentService;
import com.business.application.service.ShiftService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ShiftRepository shiftRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ShiftRepository shiftRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.shiftRepository = shiftRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentViewModel findDocumentById(String id) {
        Document document = this.documentRepository.findById(id).orElse(null);
        return document == null ? null : this.modelMapper.map(document, DocumentViewModel.class);
    }

    @Override
    public List<DocumentViewModel> getAllDocuments() {
        return this.documentRepository.findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewDocument(DocumentBindingModel documentBindingModel) {
        Document document = this.modelMapper.map(documentBindingModel, Document.class);
        this.documentRepository.saveAndFlush(document);
    }

    @Override
    public void deleteDocumentById(String id) {
        this.documentRepository.deleteById(id);
    }

    @Override
    public void createReport() {
        List<Shift> allShifts = this.shiftRepository.findAll();
        StringBuilder reportRows = new StringBuilder();
        for (Shift shift : allShifts) {
            reportRows.append(createRowForShift(shift));
        }
        String reportResult = createSumRow(allShifts);

        String dateString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        String reportName =  "???????????? " + dateString;
        String reportBody = reportName + System.lineSeparator() + reportRows.toString() + reportResult;

        DocumentBindingModel documentBindingModel = new DocumentBindingModel();
        documentBindingModel.setDocumentName(reportName);
        documentBindingModel.setDocumentContent(reportBody.getBytes(StandardCharsets.UTF_8));
        this.addNewDocument(documentBindingModel);

    }

    private String createSumRow(List<Shift> allShifts) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.lineSeparator())
                .append("???????? ???????????????????? ?????????????? ????????: ")
                .append(allShifts.stream().reduce(0, (result, shift) -> result + shift.getBarCount(), Integer::sum))
                .append(System.lineSeparator())
                .append("???????? ?????????????? ????????: ")
                .append(allShifts.stream().reduce(0, (result, shift) -> result + shift.getWasteCount(), Integer::sum));
        return stringBuilder.toString();
    }

    private String createRowForShift(Shift shift) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("??????????: ")
                .append(getShiftType(shift.getShiftType()))
                .append(", ????????: ")
                .append(new SimpleDateFormat("dd-MM-yyyy").format(shift.getDateOfShift()))
                .append(", ????????????????: ")
                .append(shift.getEmployee().getFirstName())
                .append(" ")
                .append(shift.getEmployee().getLastName())
                .append(". ???????? ???????????????????? ??????????????: ")
                .append(shift.getBarCount())
                .append(", ???????? ?????????????? ????????: ")
                .append(shift.getWasteCount())
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private String getShiftType(ShiftType shiftType) {
        switch (shiftType) {
            case FIRST:
                return "??????????";
            case SECOND:
                return "??????????";
            case THIRD:
                return "??????????";
        }
        return "";
    }
}
