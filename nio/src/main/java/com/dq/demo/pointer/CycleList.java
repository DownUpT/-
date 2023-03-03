package com.dq.demo.pointer;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表
 * <p>
 * 我们使用两个指针， fast 与slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，
 * 而fast 指针向后移动两个位置。如果链表中存在环，则fast 指针最终将再次与slow 指针在环中相遇。
 * <p>
 * <p>
 * 如下图所示，设链表中环外部分的长度为, slow 指针进入环后 又走了b 的距离与fast相遇，此时，fast指针已经走完了环的n圈
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class CycleList {


    public ListNode detectCycle2(ListNode head) {
        ListNode cur = head;
        Set<ListNode> visited = new HashSet<>();
        while (cur != null) {
            if (visited.contains(cur)) {
                return cur;
            } else {
                visited.add(cur);
            }
            cur = cur.next;
        }
        return null;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }

            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }

        return null;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}