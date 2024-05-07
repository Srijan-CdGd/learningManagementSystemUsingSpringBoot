package com.iamneo.lms.lms.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iamneo.lms.lms.dto.request.StudentProfileRequest;
import com.iamneo.lms.lms.dto.response.BasicResponse;
import com.iamneo.lms.lms.model.StudentProfile;
import com.iamneo.lms.lms.service.StudentProfileService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/lms/student/profile")
public class StudentProfileController {

    StudentProfileService studentProfileService;

    // String defaultValueParam = """
    // """;

    // @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public ResponseEntity<BasicResponse<StudentProfile>> createStudentProfile(
    // @RequestParam(value = "studentProfileRequest", defaultValue = """
    // {
    // "name": "Bm5Wt1hZhrPrx2_GokObRHcp5",
    // "dateOfBirth": "2024-04-22T07:47:23.094Z",
    // "dateOfJoining": "2024-04-22T07:47:23.094Z",
    // "contact": {
    // "phoneNum": "6402952334",
    // "User Email": "example@provider.com",
    // "address": {
    // "houseNum": "string",
    // "street": "string",
    // "city": "18_YPSYMefpbOD j49m",
    // "state": "Na9HZ9xAXF1UBAOgAPgTCG7qqhaS5gpwMcAQ59nk5Vu0eIx3IWXdRP4GAl gfT",
    // "landmark": "string",
    // "pinCode": "726406"
    // }
    // },
    // "academicInfo": {
    // "institution": "string",
    // "degree": "string",
    // "major": "string",
    // "marks": 1,
    // "markType": "CGPA"
    // }
    // }
    // """) String studentProfileRequest,
    // @RequestParam("image") MultipartFile image) {
    // // var studentProfile =
    // studentProfileService.addProfile(studentProfileRequest);

    // return null;
    // }

    @PostMapping
    // (
    // consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    // )
    // public ResponseEntity<BasicResponse<StudentProfileRequest>>
    // createStudentProfile2(
    public StudentProfileRequest createStudentProfile2(
            // @RequestParam("image") MultipartFile image,
            @RequestBody @Valid StudentProfileRequest studentProfileRequest) {
        // var studentProfile = studentProfileService.addProfile(studentProfileRequest);

        return studentProfileRequest;
    }
}
