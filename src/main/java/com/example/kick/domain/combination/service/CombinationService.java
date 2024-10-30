package com.example.kick.domain.combination.service;

import com.example.kick.domain.combination.domain.*;
import com.example.kick.domain.combination.presentation.dto.CreateCombinationRequest;
import com.example.kick.domain.combination.presentation.dto.CombinationResponse;
import com.example.kick.domain.combination.presentation.dto.QueryCombinationDetailsResponse;
import com.example.kick.domain.combination.presentation.dto.QueryCombinationListResponse;
import com.example.kick.domain.combination.presentation.dto.UpdateCombinationRequest;
import com.example.kick.domain.question.domain.Question;
import com.example.kick.domain.question.domain.QuestionRepository;
import com.example.kick.domain.user.domain.User;
import com.example.kick.domain.user.domain.UserAllergyRepository;
import com.example.kick.domain.user.domain.type.Allergy;
import com.example.kick.domain.user.facade.UserFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CombinationService {

    private final UserFacade userFacade;
    private final CombinationRepository combinationRepository;
    private final CombinationLikeRepository combinationLikeRepository;
    private final UserAllergyRepository userAllergyRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public CombinationResponse create(CreateCombinationRequest request) {
        User user = userFacade.getCurrentUser();

        Combination combination = Combination.builder()
            .name(request.getName())
            .ingredient(request.getIngredient())
            .recipe(request.getRecipe())
            .imageUrl(request.getImageUrl())
            .user(user)
            .build();

        if (request.getTags() != null) {
            combination.addTags(request.getTags());
        }

        combinationRepository.save(combination);

        return new CombinationResponse(combination.getId());
    }

    @Transactional
    public CombinationResponse update(Long id, UpdateCombinationRequest request) {
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
    public void like(Long id) {
        User user = userFacade.getCurrentUser();

        CombinationLike combinationLike = new CombinationLike(
                combinationRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new),
                user
        );
        combinationLikeRepository.save(combinationLike);
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
            .likeCount(combination.getLikeCount())
            .tags(combination.getTags().stream().map(Tag::getName).collect(Collectors.toList()))
            .build();
    }

    @Transactional
    public QueryCombinationListResponse queryList() {
        User user = userFacade.getCurrentUser();

        List<Allergy> userAllergies = userAllergyRepository.findAllergiesByUserId(user.getId());

        List<Combination> combinations = combinationRepository.findByExcludingAllergies(userAllergies);

        List<QueryCombinationListResponse.CombinationResponse> combinationResponseList = combinations.stream()
            .map(combination -> QueryCombinationListResponse.CombinationResponse.builder()
                .id(combination.getId())
                .name(combination.getName())
                .imageUrl(combination.getImageUrl())
                .tags(combination.getTags().stream().map(Tag::getName).collect(Collectors.toList()))
                .likeCount(combination.getLikeCount())
                .build())
            .collect(Collectors.toList());

        return new QueryCombinationListResponse(combinationResponseList);
    }


    @Transactional
    public List<Combination> recommend(Map<String, Boolean> userResponses) {
        List<String> likedTags = userResponses.entrySet().stream()
            .filter(Map.Entry::getValue)
            .map(entry -> questionRepository.findByQuestion(entry.getKey())
                .map(Question::getTagName)
                .orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        return combinationRepository.findByTags(likedTags);
    }

    @Transactional
    public QueryCombinationListResponse queryCombinationByName(String name){
        List<Combination> combinationList = combinationRepository.findByName(name);

        List<QueryCombinationListResponse.CombinationResponse> responseList = combinationList.stream()
                .map(combination -> QueryCombinationListResponse.CombinationResponse.builder()
                        .id(combination.getId())
                        .name(combination.getName())
                        .imageUrl(combination.getImageUrl())
                        .tags(
                                combination.getTags().stream()
                                        .map(tag -> tag.getName())
                                        .collect(Collectors.toList())
                        )
                        .likeCount(combination.getLikeCount())
                        .build()
                )
                .collect(Collectors.toList());

        return new QueryCombinationListResponse(responseList);

    }
}
