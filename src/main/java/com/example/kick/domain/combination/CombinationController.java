package com.example.kick.domain.combination;

import com.example.kick.domain.combination.dto.CombinationRequest;
import com.example.kick.domain.combination.dto.CombinationResponse;
import com.example.kick.domain.combination.dto.QueryCombinationDetailsResponse;
import com.example.kick.domain.combination.dto.QueryCombinationListResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public QueryCombinationDetailsResponse queryDetail(@PathVariable("combination-id") Long combinationId) { // @PathVariable에 name 속성 사용
        return combinationService.queryDetail(combinationId);
    }

    @Operation(summary = "조합 추가")
    @PostMapping
    public CombinationResponse create(@RequestBody @Valid CombinationRequest request) {
        return combinationService.create(request);
    }

    @Operation(summary = "조합 수정")
    @PatchMapping("/{combination-id}")
    public CombinationResponse update(
        @PathVariable("combination-id") Long id,
        @RequestBody @Valid CombinationRequest request) {
        return combinationService.update(id, request);
    }

    @Operation(summary = "조합 삭제")
    @DeleteMapping("/{combination-id}")
    public void delete(@PathVariable("combination-id") Long id) {
        combinationService.delete(id);
    }
}
