package com.reverse.postservice.mockRepos;

import com.reverse.postservice.models.Comment;
import com.reverse.postservice.repositories.CommentDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class MockCommentDao implements CommentDao {

    @Override
    public void postComment(int postId, int userId, String message, Timestamp createdDate) {

    }

    @Override
    public void deleteComment(int postId) {

    }

    @Override
    public List<Comment> getAllCommentsOnPost(int postId) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Comment> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Comment entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Comment> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Comment> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Comment> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Comment> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Comment> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Comment> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Comment getOne(Integer integer) {
        return null;
    }

    @Override
    public Comment getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Comment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Comment> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Comment> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Comment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Comment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Comment> boolean exists(Example<S> example) {
        return false;
    }
}
