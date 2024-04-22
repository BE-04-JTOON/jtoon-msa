package shop.jtoon.repository;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ListRedisRepository {

	private final RedisTemplate<String, Object> redisTemplate;
	private final ListOperations<String, Object> listOperations;
	private final Jackson2HashMapper hashMapper;

	public ListRedisRepository(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
		listOperations = redisTemplate.opsForList();
		hashMapper = new Jackson2HashMapper(false);
	}

	public void push(String key, Object value, Duration timeout) {
		listOperations.leftPush(key, hashMapper.toHash(value));
		redisTemplate.expire(key, timeout);
	}

	public void delete(String key) {
		redisTemplate.expireAt(key, new Date());
	}

	public List<Object> pop(String key, Long count) {
		return listOperations.rightPop(key, count);
	}

	public Long size(String key) {
		return listOperations.size(key);
	}
}