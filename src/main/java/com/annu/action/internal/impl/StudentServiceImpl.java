package com.annu.action.internal.impl;

import com.annu.action.internal.repository.StudentRepository;
import com.annu.action.service.StudentService;
import com.annu.action.vo.StudentExportVo;
import com.annu.action.vo.StudentVo;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentVo findOne(Long id) {
        return StudentVo.fromDto(studentRepository.findById(id));
    }

    @Override
    public StudentVo findByEqualToName(String name) {
        return StudentVo.fromDto(studentRepository.findByEqualToName(name));
    }

    @Override
    public void export(String name, HttpServletResponse response) {
        String filename = new String("学生列表.csv".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Writer writer;
        try {
            writer = response.getWriter();
            writer.write('\ufeff');
        } catch (IOException e) {
            log.warn("导出打款中的订单-获取response writer失败", e);
            return;
        }
        final CustomMappingStrategy<StudentExportVo> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(StudentExportVo.class);
        StatefulBeanToCsv<StudentExportVo> csvWriter =
                new StatefulBeanToCsvBuilder<StudentExportVo>(writer)
                        .withMappingStrategy(mappingStrategy)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                        .build();
        List<StudentExportVo> students = studentRepository.findListByEqualToName(name)
                .stream()
                .map(StudentExportVo::fromDto)
                .collect(toList());

        try {
            csvWriter.write(students);
        } catch (Exception e) {
            log.warn("导出打款中的订单-导出数据时失败", e);
        }
    }
}
