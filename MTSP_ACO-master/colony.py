from ant import Ant


class Colony:
    def __init__(self, alpha=1, beta=3):
        '''设定参数
        '''
        self.alpha = alpha
        self.beta = beta

    def get_ants(self, size):
        '''返回蚂蚁列表
        '''
        return [Ant(self.alpha, self.beta) for _ in range(size)]
