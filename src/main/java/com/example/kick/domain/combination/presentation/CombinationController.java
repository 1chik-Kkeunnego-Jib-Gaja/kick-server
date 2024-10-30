package com.example.kick.domain.combination.presentation;

import com.example.kick.domain.combination.domain.Combination;
import com.example.kick.domain.combination.domain.Tag;
import com.example.kick.domain.combination.presentation.dto.CombinationResponse;
import com.example.kick.domain.combination.presentation.dto.CreateCombinationRequest;
import com.example.kick.domain.combination.presentation.dto.QueryCombinationDetailsResponse;
import com.example.kick.domain.combination.presentation.dto.QueryCombinationListResponse;
import com.example.kick.domain.combination.presentation.dto.UpdateCombinationRequest;
import com.example.kick.domain.combination.service.CombinationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/combinations")
@RestController
public class CombinationController {

    private final CombinationService combinationService;

    @Operation(summary = "조합 리스트 조회")
    @GetMapping
    public QueryCombinationListResponse queryList() {
        return combinationService.queryList();
    }

    @Operation(summary = "조합 상세 조회")
    @GetMapping("/{combination-id}")
    public QueryCombinationDetailsResponse queryDetail(@PathVariable(value = "combination-id", required = true) Long combinationId) { // @PathVariable에 name 속성 사용
        return combinationService.queryDetail(combinationId);
    }

    @Operation(summary = "test")
    @GetMapping("/search")
    public QueryCombinationListResponse queryCombinationByName(@RequestParam("query") String query) {
        return combinationService.queryCombinationByName(query);
    }

    @Operation(summary = "조합 추가")
    @PostMapping
    public CombinationResponse create(@RequestBody @Valid CreateCombinationRequest request) {
        return combinationService.create(request);
    }

    @Operation(summary = "조합 수정")
    @PatchMapping("/{combination-id}")
    public CombinationResponse update(
        @PathVariable("combination-id") Long id,
        @RequestBody @Valid UpdateCombinationRequest request) {
        return combinationService.update(id, request);
    }

    @Operation(summary = "조합 삭제")
    @DeleteMapping("/{combination-id}")
    public void delete(@PathVariable("combination-id") Long id) {
        combinationService.delete(id);
    }

    @Operation(summary = "조합 좋아요")
    @PostMapping("/like/{combination-id}")
    public ResponseEntity<Void> like(@PathVariable("combination-id") Long id) {
        combinationService.like(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "메뉴 추천받기")
    @PostMapping("/recommend")
    public ResponseEntity<QueryCombinationListResponse> getRecommendations(@RequestBody Map<String, Boolean> userResponses) {
        List<Combination> combinations = combinationService.recommend(userResponses);
        List<QueryCombinationListResponse.CombinationResponse> responses = combinations.stream()
            .map(combination -> QueryCombinationListResponse.CombinationResponse.builder()
                .id(combination.getId())
                .name(combination.getName())
                .imageUrl(combination.getImageUrl())
                .tags(combination.getTags().stream().map(Tag::getName).collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList());

        return ResponseEntity.ok(new QueryCombinationListResponse(responses));
    }
}
