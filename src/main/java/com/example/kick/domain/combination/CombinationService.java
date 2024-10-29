package com.example.kick.domain.combination;

import com.example.kick.domain.combination.dto.CombinationRequest;
import com.example.kick.domain.combination.dto.CombinationResponse;
import com.example.kick.domain.combination.dto.QueryCombinationDetailsResponse;
import com.example.kick.domain.combination.dto.QueryCombinationListResponse;
import com.example.kick.domain.user.User;
import com.example.kick.domain.user.UserFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CombinationService {

    private final UserFacade userFacade;
    private final CombinationRepository combinationRepository;

    @Transactional
    public CombinationResponse create(CombinationRequest request) {
        User user = userFacade.getCurrentUser();

        Combination combination = Combination.builder()
            .name(request.getName())
            .ingredient(request.getIngredient())
            .recipe(request.getRecipe())
            .imageUrl(request.getImageUrl())
            .user(user)
            .build();

        combinationRepository.save(combination);

        return new CombinationResponse(combination.getId());
    }

    @Transactional
    public CombinationResponse update(Long id, CombinationRequest request) {
        Combination combination = combinationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("combination not found"));

        combination.update(request);

        return new CombinationResponse(combination.getId());
    }

    @Transactional
    public void delete(Long id) {
        Combination combination = combinationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("combination not found"));

        combinationRepository.delete(combination);
    }

    @Transactional
    public QueryCombinationDetailsResponse queryDetail(Long id) {
        User user = userFacade.getCurrentUser();
        Combination combination = combinationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("combination not found"));

        return QueryCombinationDetailsResponse.builder()
            .name(combination.getName())
            .ingredient(combination.getIngredient())
            .recipe(combination.getRecipe())
            .imageUrl(combination.getImageUrl())
            .userId(user.getId())
            .build();
    }

    @Transactional
    public QueryCombinationListResponse queryList() {
        User user = userFacade.getCurrentUser();

        List<Combination> combinations = combinationRepository.findByUserId(user.getId());

        List<QueryCombinationListResponse.CombinationResponse> combinationResponseList = combinations.stream()
            .map(combination -> QueryCombinationListResponse.CombinationResponse.builder()
                .id(combination.getId())
                .name(combination.getName())
                .imageUrl(combination.getImageUrl())
                .build())
            .collect(Collectors.toList());

        return new QueryCombinationListResponse(combinationResponseList);
    }
}
