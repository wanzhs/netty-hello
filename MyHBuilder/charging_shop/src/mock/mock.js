import Mock from 'mockjs'
const Random = Mock.Random;

const productData = function() {
    let userList = [];
    for(let i=0; i < 30; i++) {
        let newObject = {
            registerTime: Random.date('yyyy-MM-dd HH:mm:ss'),
            nickName: Random.cword(4,5),
            phoneNumber: '13789574185',
            name: Random.cname(),
            userName: Random.word(5,6),
            role: {
                '超级管理员': 1,
                '销售经理': 1,
                'abc': 0
            }
        }
        userList.push(newObject);
    }
    return {
        data: userList,
        length: 90
    }
}

export default Mock.mock('http://test.com', productData)
