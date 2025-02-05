export interface MenuItem {
  name: string;
  children?: MenuItem[];
  isOpen?: boolean; // Thêm trạng thái để theo dõi dropdown
}
