package com.ssafy.db.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

import static com.ssafy.db.entity.QCourse.course;
import static com.ssafy.db.entity.QCourseReview.courseReview;

@RequiredArgsConstructor
@Repository
public class CourseReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    // 코스의 평균 점수 0~10점
    public List<Double> findAvgScoreByCourseId(int courseId) {
        return queryFactory.select(courseReview.score.avg().as("score"))
                .from(courseReview)
                .where(courseReview.course.courseId.eq(courseId))
                .fetch();
    }

    // 로그인 사용자의 코스 리뷰 점수
    public Double findScoreByCourseIdAndUserId(int courseId, String userId) {
        return queryFactory.select(courseReview.score)
                .from(courseReview)
                .where(courseReview.course.courseId.eq(courseId)
                        .and(courseReview.user.userId.eq(userId)))
                .fetchOne();
    }
}
