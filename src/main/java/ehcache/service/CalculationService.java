package ehcache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

  private final Logger logger = LoggerFactory.getLogger(CalculationService.class);

  @Cacheable(value = "areaOfCircleCache", key = "#radius", condition = "#radius > 5")
  public double areaOfCircle(int radius) {
    logger.info("calculate the area of a circle with a radius of {}", radius);
    return Math.PI * Math.pow(radius, 2);
  }

  @Cacheable(value = "powCache", key = "{#basis, #exponent}")
  public double pow(int basis, int exponent) {
    logger.info("raise {} to the power of {}", basis, exponent);
    return Math.pow(basis, exponent);
  }

  @Cacheable(value = "multiplyCache", keyGenerator = "multiplyKeyGenerator")
  public double multiply(int factor1, int factor2) {
    logger.info("Multiply {} with {}", factor1, factor2);
    return factor1 * (double)factor2;
  }

  @CacheEvict(cacheNames = {"areaOfCircleCache", "multiplyCache"}, allEntries = true)
  public void evictCache() {
    logger.info("Evict all cache entries...");
  }
}
