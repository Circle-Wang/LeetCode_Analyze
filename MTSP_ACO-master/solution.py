import functools


@functools.total_ordering
class Solution:

    def __init__(self, graph, start, ant=None):
        self.graph = graph
        self.start = start
        self.ant = ant
        self.current = start
        self.cost = 0
        self.path = []
        self.nodes = [start]
        self.visited = set(self.nodes)

    def __iter__(self):
        return iter(self.path)

    def __eq__(self, other):
        return self.cost == other.cost

    def __lt__(self, other):
        return self.cost < other.cost

    def __contains__(self, item):
        '''自定义in操作
        '''
        return item in self.visited or item == self.current

    def __repr__(self):
        easy_id = self.get_easy_id(sep=',', monospace=False)
        return '{}\t{}'.format(self.cost, easy_id)

    def get_easy_id(self, sep=' ', monospace=True):
        nodes = [str(n) for n in self.get_id()]
        if monospace:
            size = max([len(n) for n in nodes])
            nodes = [n.rjust(size) for n in nodes]
        return sep.join(nodes)

    def get_id(self):
        first = min(self.nodes)   ## node之间可以比较大小?
        index = self.nodes.index(first)
        return tuple(self.nodes[index:] + self.nodes[:index]) # 把最小的去掉了

    def add_node(self, node):
        '''将节点加入到nodes中, 并计算消耗, 更新当前节点self.current
        '''
        self.nodes.append(node)
        self.visited.add(node)
        self._add_node(node)

    def _add_node(self, node):
        edge = self.current, node
        data = self.graph.edges[edge]  # 通过两个节点得到边
        self.path.append(edge)
        self.cost += data['weight']
        self.current = node

    def close(self):
        """通过将开始节点加入到nodes中,模拟回到原点
        """
        self._add_node(self.start)
