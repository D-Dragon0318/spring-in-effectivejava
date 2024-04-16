-- Lua脚本示例
local size = redis.call('ZCARD', KEYS[1]) -- 获取当前集合大小
if size < 10 then
    redis.call('ZADD', KEYS[1], ARGV[1], ARGV[2]) -- 添加新元素
else
    redis.call('ZREMRANGEBYRANK', KEYS[1], 0, 0) -- 移除排名最低的元素
    redis.call('ZADD', KEYS[1], ARGV[1], ARGV[2]) -- 添加新元素
end